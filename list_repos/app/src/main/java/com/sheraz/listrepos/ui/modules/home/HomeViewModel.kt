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

    init {
        Logger.d(TAG, "init(): ")
        setIsLoading(false)
    }

    fun getPagedListAsLiveData(): LiveData<PagedList<GitHubRepoItem>> = appRepository.getLiveDataPagedList()

    override fun onCleared() {

        Logger.d(TAG, "onCleared(): ")
        super.onCleared()
        appRepository.cancelAllRequests()

    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}