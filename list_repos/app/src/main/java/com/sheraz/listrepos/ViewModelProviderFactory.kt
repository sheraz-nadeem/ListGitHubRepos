package com.sheraz.listrepos


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * A provider factory that persists ViewModels [ViewModel].
 * Used if the view model has a parameterized constructor.
 */

class ViewModelProviderFactory<V : Any>(
    private val viewModel: V
) : ViewModelProvider.Factory {


    init {
        Log.d(TAG, "init(): ")
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }



    /**
     * Companion object, common to all instances of this class
     * Similar to static fields in Java
     */
    companion object {
        private val TAG: String = ViewModelProviderFactory::class.java.simpleName
    }
}
