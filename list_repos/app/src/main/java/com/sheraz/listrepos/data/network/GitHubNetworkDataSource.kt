package com.sheraz.listrepos.data.network

import androidx.lifecycle.LiveData
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity

interface GitHubNetworkDataSource {

    val downloadedGitHubRepoList: LiveData<List<GitHubRepoEntity>>
    suspend fun fetchGitHubRepos(page: Int, per_page: Int)

}