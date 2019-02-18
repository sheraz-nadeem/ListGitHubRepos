package com.sheraz.listrepos.data.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity

interface AppRepository {

    val gitHubRepoEntityPagedFactory: DataSource.Factory<Int, GitHubRepoEntity>
    val gitHubRepoEntityPagedListBuilder: LivePagedListBuilder<Int, GitHubRepoEntity>

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