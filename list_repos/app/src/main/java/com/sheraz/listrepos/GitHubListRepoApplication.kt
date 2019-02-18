package com.sheraz.listrepos

import android.app.Application
import com.sheraz.listrepos.di.component.AppComponent
import com.sheraz.listrepos.di.component.DaggerAppComponent
import com.sheraz.listrepos.di.module.ContextModule
import com.sheraz.listrepos.utils.Logger

class GitHubListRepoApplication : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        Logger.d(TAG, "onCreate: ")
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private val TAG = GitHubListRepoApplication::class.java.simpleName

        private var INSTANCE: GitHubListRepoApplication? = null

        fun get(): GitHubListRepoApplication = INSTANCE!!
    }
}

class Injector private constructor() {
    companion object {
        fun get() : AppComponent = GitHubListRepoApplication.get().component
    }
}