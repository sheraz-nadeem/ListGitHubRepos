package com.sheraz.listrepos.ui.modules.home

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sheraz.listrepos.data.repository.AppRepository
import com.sheraz.listrepos.ui.models.GitHubRepoItem
import com.sheraz.listrepos.ui.modules.base.BaseViewModel
import com.sheraz.listrepos.utils.Logger

class HomeViewModel(

    private val appRepository: AppRepository

) : BaseViewModel() {

    val totalItemsCount = ObservableInt(0)

    init {
        Logger.d(TAG, "init(): ")
        setIsLoading(false)
    }

    fun fetchGitHubReposFromNetworkByPage(page: Int) {

        Logger.d(TAG, "fetchGitHubReposFromNetworkByPage(): page: $page")
        setIsLoading(true)
//        appRepository.fetchGitHubReposFromNetwork(page)

    }

    fun getPagedListAsLiveData(): LiveData<PagedList<GitHubRepoItem>> = appRepository.getLiveDataPagedList()

    override fun onCleared() {

        Logger.d(TAG, "onCleared(): ")
        super.onCleared()
        appRepository.cancelAllRequests()

    }

    fun setTotalItemsCount(count: Int) {
        totalItemsCount.set(count)
    }

    fun getTotalItemsCount(): Int {
        return totalItemsCount.get()
    }


    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}