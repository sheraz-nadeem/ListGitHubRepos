package com.sheraz.listrepos.di.module

import com.sheraz.listrepos.utils.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggerModule {

    @Provides
    @Singleton
    fun provideLogger() = Logger()
}