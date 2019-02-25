package com.sheraz.listrepos.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sheraz.listrepos.ui.models.GitHubRepoItem

interface AppRepository {

    val pagedListConfig: PagedList.Config

    fun getLiveDataPagedList() : LiveData<PagedList<GitHubRepoItem>>
    fun cancelAllRequests()


    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        const val NETWORK_PAGE_SIZE = 15
        const val DATABASE_PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 5
    }
}