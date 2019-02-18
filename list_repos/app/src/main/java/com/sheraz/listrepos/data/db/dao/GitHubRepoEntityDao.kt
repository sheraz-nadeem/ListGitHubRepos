package com.sheraz.listrepos.data.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity

@Dao
interface GitHubRepoEntityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(gitHubRepoEntity:  GitHubRepoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(gitHubRepoEntityList: List<GitHubRepoEntity>)

    @Query("SELECT * FROM github_repo")
    fun getAllRepos(): LiveData<List<GitHubRepoEntity>>

    @Query("SELECT * FROM github_repo")
    fun getAllReposPaged(): DataSource.Factory<Int, GitHubRepoEntity>

    @Query("SELECT * FROM github_repo WHERE fork = :forked")
    fun getReposByForkedCondition(forked: Boolean = false): LiveData<List<GitHubRepoEntity>>

    @Query("DELETE FROM github_repo")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM github_repo")
    fun getNumOfRows(): Int
}