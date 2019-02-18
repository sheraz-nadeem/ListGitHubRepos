package com.sheraz.listrepos.di.module

import androidx.lifecycle.ViewModelProvider
import com.sheraz.listrepos.ViewModelProviderFactory
import com.sheraz.listrepos.data.repository.AppRepository
import com.sheraz.listrepos.ui.modules.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideViewModelFactory(homeViewModel: HomeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(homeViewModel)
    }

    @Provides
    fun provideHomeViewModel(appRepository: AppRepository): HomeViewModel {
        return HomeViewModel(appRepository)
    }

}