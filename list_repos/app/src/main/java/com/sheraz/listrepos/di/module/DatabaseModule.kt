package com.sheraz.listrepos.di.module

import android.content.Context
import androidx.room.Room
import com.sheraz.listrepos.data.db.GitHubRepoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): GitHubRepoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            GitHubRepoDatabase::class.java, "github_repo_db"
        ).build()
    }

}