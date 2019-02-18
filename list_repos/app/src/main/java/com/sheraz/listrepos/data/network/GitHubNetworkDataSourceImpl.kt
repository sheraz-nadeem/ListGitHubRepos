package com.sheraz.listrepos.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.utils.Logger

class GitHubNetworkDataSourceImpl(
    private val gitHubApiService: GitHubApiService
) : GitHubNetworkDataSource {


    init {
        Logger.d(TAG, "init(): ")
    }

    /**
     * Encapsulating Mutable value so that it can only be changed within the "fetchGitHubRepos()" method
     */
    private val _downloadedGitHubRepoList = MutableLiveData<List<GitHubRepoEntity>>()
    override val downloadedGitHubRepoList: LiveData<List<GitHubRepoEntity>>
        get() = _downloadedGitHubRepoList


    override suspend fun fetchGitHubRepos(page: Int, per_page: Int) {

        Logger.d(TAG, "fetchGitHubRepos(): page: $page, per_page: $per_page")

        try {

            val fetchedReposWithPage = gitHubApiService
                .getReposWithPageAsync(page, per_page)
                .await()

            Logger.v(TAG, "fetchGitHubRepos(): fetchedReposWithPage: ${fetchedReposWithPage.size}")
            Logger.v(TAG, "fetchGitHubRepos(): fetchedReposWithPage: $fetchedReposWithPage")
            // MutableLiveData.postValue must be called from background thread,
            // that is why this function is a "suspend" function which will
            // be called from a coroutine.
            // Finally, postValue will post a task on "main thread" to set the
            // given value
            _downloadedGitHubRepoList.postValue(fetchedReposWithPage)

        } catch (e: Exception) {
            Logger.e(TAG, "fetchGitHubRepos(): Exception occurred, Error => " + e.message)
        }
    }


    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        private val TAG: String = GitHubNetworkDataSourceImpl::class.java.simpleName
    }
}