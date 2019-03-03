package com.sheraz.listrepos.internal

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sheraz.listrepos.utils.Logger
import java.io.IOException


/**
 * AppCompatActivity Extension functions
 */

inline fun <reified T : Fragment> AppCompatActivity.findFragmentByTag(tag: String) : T? = supportFragmentManager.findFragmentByTag(tag) as? T

inline fun <reified T : Fragment> AppCompatActivity.findFragmentByTagWithAutoDismiss(tag: String) : T? {
    val fragment = supportFragmentManager.findFragmentByTag(tag) as? T
    when (fragment) {
        is DialogFragment -> fragment.dismiss()
    }
    return fragment
}

inline fun <reified V : ViewModel> AppCompatActivity.bindViewModel(viewModelFactory: ViewModelProvider.Factory) = lazy { ViewModelProviders.of(this, viewModelFactory).get(V::class.java) }


/**
 * Network Utility Extension functions  
 */
suspend fun safeApiCall(networkBlock: suspend () -> Unit, failureBlock: (Exception) -> Unit, errorMessage: String) {
    return try {
        networkBlock()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Logger.e("safeApiCall", "safeApiCall(): Exception occurred, Error => " + e.message)
        failureBlock(IOException(errorMessage, e))
    }
}