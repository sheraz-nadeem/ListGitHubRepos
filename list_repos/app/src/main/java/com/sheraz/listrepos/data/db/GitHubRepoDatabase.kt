package com.sheraz.listrepos.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sheraz.listrepos.data.db.dao.GitHubRepoEntityDao
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity


@Database(
    entities = [GitHubRepoEntity::class],
    version = 1,
    exportSchema = false // Just to get rid of the warning generated at build time
)

abstract class GitHubRepoDatabase : RoomDatabase() {


    /**********
     *  All Dao Methods
     **********/

    abstract fun gitHubRepoEntityDao(): GitHubRepoEntityDao

}