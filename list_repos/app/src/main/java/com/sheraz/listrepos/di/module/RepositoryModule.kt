package com.sheraz.listrepos.di.module

import com.sheraz.listrepos.data.CoroutinesDispatcherProvider
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.mapper.DbRepoMapper
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.data.repository.AppRepository
import com.sheraz.listrepos.data.repository.AppRepositoryImpl
import com.sheraz.listrepos.utils.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        logger: Logger,
        dbRepoMapper: DbRepoMapper,
        dao: GitHubRepoEntityDao,
        networkDataSource: GitHubNetworkDataSource,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): AppRepository = AppRepositoryImpl(logger, dbRepoMapper, dao, networkDataSource, dispatcherProvider)

}