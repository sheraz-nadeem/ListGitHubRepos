package com.sheraz.listrepos.ui.modules.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.sheraz.listrepos.Injector
import com.sheraz.listrepos.utils.Logger

abstract class BaseViewModel: ViewModel() {


    val isLoading = ObservableBoolean(false)
    protected val logger: Logger = Injector.get().logger()

    init {
        logger.d(TAG, "init(): ")
    }


    fun getIsLoading(): ObservableBoolean {
        return isLoading
    }

    fun setIsLoading(loading: Boolean) {
        isLoading.set(loading)
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        private val TAG = BaseViewModel::class.java.simpleName
    }
}