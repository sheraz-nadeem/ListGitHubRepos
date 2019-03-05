package com.sheraz.listrepos.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.data.db.mapper.DbRepoMapper
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.utils.Logger
import kotlinx.coroutines.*
import kotlin.Exception

class AppRepositoryImpl(

    private val dbRepoMapper: DbRepoMapper,
    private val gitHubRepoEntityDao: GitHubRepoEntityDao,
    private val gitHubNetworkDataSource: GitHubNetworkDataSource,
    private val dispatcherProvider: CoroutinesDispatcherProvider

) : AppRepository {

    override val pagedListConfig: PagedList.Config

    private val _isFetchInProgress = MutableLiveData<Boolean>()
    override val isFetchInProgress: LiveData<Boolean>
        get() = _isFetchInProgress

    private val _networkError = MutableLiveData<Exception>()
    override val networkError: LiveData<Exception>
        get() = _networkError

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)


    init {
//        Logger.d(TAG, "init(): ")

        pagedListConfig =
            PagedList.Config.Builder()
                .setPrefetchDistance(AppRepository.PREFETCH_DISTANCE)
                .setPageSize(AppRepository.DATABASE_PAGE_SIZE)
                .setInitialLoadSizeHint(AppRepository.DATABASE_PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build()

        // Repository isn't lifecycle-aware, so we observe on NetworkDataSource "forever",
        // As soon as the data is fetched from the network it is persisted in DB immediately
        gitHubNetworkDataSource.downloadedGitHubRepoList.observeForever { result ->

            _isFetchInProgress.postValue(false)
            result
                .onSuccess { persistDownloadedGitHubRepoEntityList(it) }
                .onFailure { _networkError.postValue(it as Exception)  }

        }
    }

    override fun clearCache() {

//        Logger.d(TAG, "clearCache(): ")

        scope.launch(dispatcherProvider.ioDispatcher) {
            try {

                val numOfRowsDeleted = gitHubRepoEntityDao.deleteAll()
//                Logger.i(TAG, "clearCache(): numOfRowsDeleted: $numOfRowsDeleted")

            } catch (e: Exception) {

//                Logger.e(TAG, "clearCache(): Exception occurred, Error => ${e.message}")

            }

        }

    }

    override fun refreshReposList() {

//        Logger.d(TAG, "refreshReposList(): ")
        fetchGitHubReposFromNetworkAndPersist(-1)

    }

    override fun getLiveDataPagedList() : LiveData<PagedList<GitHubRepoItem>> {

//        Logger.d(TAG, "getLiveDataPagedList(): ")
        return LivePagedListBuilder<Int, GitHubRepoItem>(getAllReposPagedFactory(), pagedListConfig).setBoundaryCallback(RepoBoundaryCallback()).build()

    }

    private fun getAllReposPagedFactory() : DataSource.Factory<Int, GitHubRepoItem> {

//        Logger.d(TAG, "getAllReposPagedFactory(): ")
        return gitHubRepoEntityDao.getAllReposPaged().map { dbRepoMapper.fromDb(it) }

    }

    private fun persistDownloadedGitHubRepoEntityList(gitHubRepoEntityList: List<GitHubRepoEntity>) {

//        Logger.d(TAG, "persistDownloadedGitHubRepoEntityList(): gitHubRepoEntityList.size: ${gitHubRepoEntityList.size}")

        scope.launch(dispatcherProvider.ioDispatcher) {
            try {

                if (gitHubRepoEntityList.isNotEmpty()) {
                    gitHubRepoEntityDao.insertList(gitHubRepoEntityList)
                }

            } catch (e: Exception) {

//                Logger.e(TAG, "persistDownloadedGitHubRepoEntityList(): Exception occurred, Error => ${e.localizedMessage}")

            }

        }

    }

    private fun fetchGitHubReposFromNetworkAndPersist(page: Int = 1, per_page: Int = AppRepository.NETWORK_PAGE_SIZE) {

//        Logger.d(TAG, "fetchGitHubReposFromNetworkAndPersist(): page: $page, per_page: $per_page")

        scope.launch(dispatcherProvider.ioDispatcher) {

            _isFetchInProgress.postValue(true)
            val numOfRows = getNumOfRows()
            val actualPageSize = getActualPageSize(page, numOfRows)
//            Logger.i(TAG, "fetchGitHubReposFromNetworkAndPersist(): numOfRows: $numOfRows, actualPageSize: $actualPageSize")

            gitHubNetworkDataSource.loadGitHubRepos(actualPageSize, per_page)
        }

    }

    private fun getNumOfRows(): Int {
        return gitHubRepoEntityDao.getNumOfRows()
    }

    private fun getActualPageSize(page: Int, numOfRows: Int): Int {
        return when (page > 0) {
            true -> (numOfRows / AppRepository.NETWORK_PAGE_SIZE) + 1
            false -> 1 // We need to refresh data
        }
    }

    override fun cancelAllRequests() {

//        Logger.d(TAG, "cancelAllRequests(): ")
        parentJob.cancelChildren()

    }


    inner class RepoBoundaryCallback : PagedList.BoundaryCallback<GitHubRepoItem>() {

        /**
         * Database returned 0 items. We should query the backend for more items.
         */
        override fun onZeroItemsLoaded() {
//            Logger.d(TAG_REPO_BOUNDARY_CALLBACK, "onZeroItemsLoaded(): ")
            requestAndSaveData()
        }

        /**
         * When all items in the database were loaded, we need to query the backend for more items.
         */
        override fun onItemAtEndLoaded(itemAtEnd: GitHubRepoItem) {
//            Logger.d(TAG_REPO_BOUNDARY_CALLBACK, "onItemAtEndLoaded(): ")
            requestAndSaveData()
        }

        private fun requestAndSaveData() {

            if (_isFetchInProgress.value!!) return
            fetchGitHubReposFromNetworkAndPersist()

        }
    }


    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        private val TAG: String = AppRepositoryImpl::class.java.simpleName
        private val TAG_REPO_BOUNDARY_CALLBACK: String = RepoBoundaryCallback::class.java.simpleName
    }
}