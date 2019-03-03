package com.sheraz.listrepos.data.network

import androidx.lifecycle.LiveData
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity

interface GitHubNetworkDataSource {

    val downloadedGitHubRepoList: LiveData<Result<List<GitHubRepoEntity>>>
    suspend fun loadGitHubRepos(page: Int, per_page: Int)

}