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
import java.lang.Exception

class AppRepositoryImpl(

    private val dbRepoMapper: DbRepoMapper,
    private val gitHubRepoEntityDao: GitHubRepoEntityDao,
    private val gitHubNetworkDataSource: GitHubNetworkDataSource,
    private val dispatcherProvider: CoroutinesDispatcherProvider

) : AppRepository {

    override val pagedListConfig: PagedList.Config
    override val isFetchInProgress: LiveData<Boolean>
        get() = _isFetchInProgress

    private val _isFetchInProgress = MutableLiveData<Boolean>()
    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)


    init {
        Logger.d(TAG, "init(): ")

        _isFetchInProgress.value = false

        pagedListConfig =
            PagedList.Config.Builder()
                .setPrefetchDistance(AppRepository.PREFETCH_DISTANCE)
                .setPageSize(AppRepository.DATABASE_PAGE_SIZE)
                .setInitialLoadSizeHint(AppRepository.DATABASE_PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build()

        // Repository isn't lifecycle-aware, so we observe on NetworkDataSource "forever",
        // As soon as the data is fetched from the network it is persisted in DB immediately
        gitHubNetworkDataSource.downloadedGitHubRepoList.observeForever { gitHubRepoEntityList ->
            persistDownloadedGitHubRepoEntityList(gitHubRepoEntityList)
        }
    }

    override fun getLiveDataPagedList() : LiveData<PagedList<GitHubRepoItem>> {

        Logger.d(TAG, "getLiveDataPagedList(): ")
        return LivePagedListBuilder<Int, GitHubRepoItem>(getAllReposPagedFactory(), pagedListConfig).setBoundaryCallback(RepoBoundaryCallback()).build()

    }

    private fun getAllReposPagedFactory() : DataSource.Factory<Int, GitHubRepoItem> {

        Logger.d(TAG, "getAllReposPagedFactory(): ")
        return gitHubRepoEntityDao.getAllReposPaged().map { dbRepoMapper.fromDb(it) }

    }

    private fun persistDownloadedGitHubRepoEntityList(gitHubRepoEntityList: List<GitHubRepoEntity>) {

        Logger.d(TAG, "persistDownloadedGitHubRepoEntityList(): gitHubRepoEntityList.size: ${gitHubRepoEntityList.size}")

        scope.launch(dispatcherProvider.ioDispatcher) {
            try {

                if (gitHubRepoEntityList.isNotEmpty()) {
                    gitHubRepoEntityDao.insertList(gitHubRepoEntityList)
                }

                _isFetchInProgress.postValue(false)

            } catch (e: Exception) {

                Logger.e(TAG, "persistDownloadedGitHubRepoEntityList(): Exception occurred, Error => ${e.localizedMessage}")

            }

        }

    }

    private fun fetchGitHubReposFromNetworkAndPersist(page: Int = 1, per_page: Int = AppRepository.NETWORK_PAGE_SIZE) {

        Logger.d(TAG, "fetchGitHubReposFromNetworkAndPersist(): page: $page, per_page: $per_page")

        scope.launch(dispatcherProvider.ioDispatcher) {

            _isFetchInProgress.postValue(true)
            val numOfRows = getNumOfRows()
            val actualPageSize = (numOfRows / AppRepository.NETWORK_PAGE_SIZE) + 1
            Logger.i(TAG, "fetchGitHubReposFromNetworkAndPersist(): numOfRows: $numOfRows, actualPageSize: $actualPageSize")

            gitHubNetworkDataSource.fetchGitHubRepos(actualPageSize, per_page)
        }

    }

    private fun getNumOfRows(): Int {
        return gitHubRepoEntityDao.getNumOfRows()
    }

    override fun cancelAllRequests() {

        Logger.d(TAG, "cancelAllRequests(): ")
        parentJob.cancelChildren()

    }


    inner class RepoBoundaryCallback : PagedList.BoundaryCallback<GitHubRepoItem>() {

        /**
         * Database returned 0 items. We should query the backend for more items.
         */
        override fun onZeroItemsLoaded() {
            Logger.d(TAG_REPO_BOUNDARY_CALLBACK, "onZeroItemsLoaded(): ")
            requestAndSaveData()
        }

        /**
         * When all items in the database were loaded, we need to query the backend for more items.
         */
        override fun onItemAtEndLoaded(itemAtEnd: GitHubRepoItem) {
            Logger.d(TAG_REPO_BOUNDARY_CALLBACK, "onItemAtEndLoaded(): ")
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