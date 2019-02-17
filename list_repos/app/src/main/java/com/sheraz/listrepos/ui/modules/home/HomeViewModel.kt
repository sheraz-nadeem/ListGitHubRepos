package com.sheraz.listrepos.ui.modules.home

import androidx.databinding.ObservableInt
import com.sheraz.listrepos.ui.modules.base.BaseViewModel
import com.sheraz.listrepos.utils.Logger

class HomeViewModel: BaseViewModel() {

    val totalItemsCount = ObservableInt(0)

    init {
        Logger.d(TAG, "init(): ")
        setIsLoading(false)
    }

    fun fetchRepos() {
        setIsLoading(true)
    }


    override fun onCleared() {
        super.onCleared()
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