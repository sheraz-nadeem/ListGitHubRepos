package com.sheraz.listrepos.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import com.sheraz.listrepos.internal.safeApiCall
import com.sheraz.listrepos.utils.Logger
import java.io.IOException

class GitHubNetworkDataSourceImpl(
    private val gitHubApiService: GitHubApiService
) : GitHubNetworkDataSource {


    init {
//        Logger.d(TAG, "init(): ")
    }

    /**
     * Encapsulating Mutable value so that it can only be changed within the "fetchGitHubRepos()" method
     */
    private val _downloadedGitHubRepoList = MutableLiveData<Result<List<GitHubRepoEntity>>>()
    override val downloadedGitHubRepoList: LiveData<Result<List<GitHubRepoEntity>>>
        get() = _downloadedGitHubRepoList


    /**
     * Public method to initiate get repos using [safeApiCall] extension function
     * Since, we are doing IO operations with API using Retrofit, this method should be a
     * suspend function and called from a coroutine with IO Dispatcher or another suspend function.
     */
    override suspend fun loadGitHubRepos(page: Int, per_page: Int) {

//        Logger.d(TAG, "loadGitHubRepos(): page: $page, per_page: $per_page")
        safeApiCall(
            networkBlock = { fetchGitHubRepos(page, per_page) },
            failureBlock = { _downloadedGitHubRepoList.postValue(Result.failure(it)) },
            errorMessage = "Error loading github repos data ")

    }

    /**
     * Private method to get Repos using [GitHubApiService] and post the received list of
     * repos on the MutableLiveData
     */
    private suspend fun fetchGitHubRepos(page: Int, per_page: Int) {

//        Logger.d(TAG, "fetchGitHubRepos(): page: $page, per_page: $per_page")

        val response = gitHubApiService.getReposWithPageAsync(page, per_page).await()

        if (response.isSuccessful) {

            response.body()?.let {
                // MutableLiveData.postValue will post a task on
                // main thread to set the given value
                // We cannot use MutableLiveData.setValue here
                _downloadedGitHubRepoList.postValue(Result.success(it))
            } ?: throw IOException(" Throwing exception ${response.code()} ${response.message()}")

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