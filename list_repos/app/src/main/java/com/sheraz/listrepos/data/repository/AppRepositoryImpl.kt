package com.sheraz.listrepos.data.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.utils.Logger
import kotlinx.coroutines.*

class AppRepositoryImpl(

    private val gitHubRepoEntityDao: GitHubRepoEntityDao,
    private val gitHubNetworkDataSource: GitHubNetworkDataSource,
    private val dispatcherProvider: CoroutinesDispatcherProvider

) : AppRepository {


    override val gitHubRepoEntityPagedFactory: DataSource.Factory<Int, GitHubRepoEntity>
    override val gitHubRepoEntityPagedListBuilder: LivePagedListBuilder<Int, GitHubRepoEntity>

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    init {
        Logger.d(TAG, "init(): ")

        gitHubRepoEntityPagedFactory = gitHubRepoEntityDao.getAllReposPaged()
        gitHubRepoEntityPagedListBuilder =
            LivePagedListBuilder<Int, GitHubRepoEntity>(gitHubRepoEntityPagedFactory, AppRepository.PER_PAGE)

        // Repository isn't lifecycle-aware, so we observe on NetworkDataSource "forever",
        // As soon as the data is fetched from the network it is persisted in DB immediately
        gitHubNetworkDataSource.downloadedGitHubRepoList.observeForever { gitHubRepoEntityList ->
            persistDownloadedGitHubRepoEntityList(gitHubRepoEntityList)
        }
    }

    private fun persistDownloadedGitHubRepoEntityList(gitHubRepoEntityList: List<GitHubRepoEntity>) {

        Logger.d(TAG, "persistDownloadedGitHubRepoEntityList(): gitHubRepoEntityList: $gitHubRepoEntityList")

        scope.launch(dispatcherProvider.ioDispatcher) {
            gitHubRepoEntityDao.insertList(gitHubRepoEntityList)
        }

    }

    override fun fetchGitHubReposFromNetwork(page: Int, per_page: Int) {

        Logger.d(TAG, "fetchGitHubReposFromNetwork(): page: $page, per_page: $per_page")

        scope.launch(dispatcherProvider.ioDispatcher) {

            val numOfRows = getNumOfRows()
            Logger.i(TAG, "fetchGitHubReposFromNetwork(): numOfRows: $numOfRows")

            gitHubNetworkDataSource.fetchGitHubRepos(page, per_page)
        }

    }

    private suspend fun getNumOfRows(): Int {
        return gitHubRepoEntityDao.getNumOfRows()
    }

    override fun cancelAllRequests() {

        Logger.d(TAG, "cancelAllRequests(): ")
        parentJob.cancelChildren()

    }



    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        private val TAG: String = AppRepositoryImpl::class.java.simpleName
    }
}