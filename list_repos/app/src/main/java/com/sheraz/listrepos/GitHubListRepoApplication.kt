package com.sheraz.listrepos

import android.app.Application
import com.sheraz.listrepos.utils.Logger

class GitHubListRepoApplication : Application() {

    override fun onCreate() {
        Logger.d(TAG, "onCreate: ")
        super.onCreate()
    }



    companion object {
        private val TAG = GitHubListRepoApplication::class.java.simpleName
    }
}