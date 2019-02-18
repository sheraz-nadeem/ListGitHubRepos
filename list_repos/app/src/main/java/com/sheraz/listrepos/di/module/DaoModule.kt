package com.sheraz.listrepos.di.module

import com.sheraz.listrepos.data.db.GitHubRepoDatabase
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import dagger.Module
import dagger.Provides


@Module
class DaoModule {

    @Provides
    fun provideGitHubRepoEntityDao(database: GitHubRepoDatabase): GitHubRepoEntityDao = database.gitHubRepoEntityDao()

}