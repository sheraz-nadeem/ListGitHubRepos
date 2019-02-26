package com.sheraz.listrepos.di.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.sheraz.listrepos.data.db.GitHubRepoDatabase
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.network.GitHubApiService
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.data.repository.AppRepository
import com.sheraz.listrepos.di.module.*
import com.sheraz.listrepos.ui.modules.adapters.HomeAdapter
import com.sheraz.listrepos.ui.modules.home.HomeViewModel
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        CoroutinesDispatcherProviderModule::class,
        DatabaseModule::class,
        DaoModule::class,
        RepositoryModule::class,
        HomeModule::class
    ]
)
interface AppComponent {

    fun appContext(): Context
    fun database(): GitHubRepoDatabase
    fun gitHubRepoEntityDao(): GitHubRepoEntityDao
    fun retrofit(): Retrofit
    fun gitHubRepoApiService(): GitHubApiService
    fun gitHubNetworkDataSource(): GitHubNetworkDataSource
    fun picasso(): Picasso
    fun appRepository(): AppRepository
    fun homeAdapter(): HomeAdapter
    fun homeViewModel(): HomeViewModel
    fun viewModelFactory(): ViewModelProvider.Factory

}