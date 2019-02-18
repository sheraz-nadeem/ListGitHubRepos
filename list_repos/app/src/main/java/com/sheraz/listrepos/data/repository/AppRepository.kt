package com.sheraz.listrepos.data.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.sheraz.listrepos.ui.models.GitHubRepoItem

interface AppRepository {

    val gitHubRepoEntityPagedFactory: DataSource.Factory<Int, GitHubRepoItem>
    val gitHubRepoEntityPagedListBuilder: LivePagedListBuilder<Int, GitHubRepoItem>

    fun fetchGitHubReposFromNetwork(page: Int, per_page: Int = PER_PAGE)
    fun cancelAllRequests()


    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        const val PER_PAGE = 10
    }
}