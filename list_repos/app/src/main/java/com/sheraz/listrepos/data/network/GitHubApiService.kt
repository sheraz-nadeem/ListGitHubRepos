package com.sheraz.listrepos.data.network

import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("repos")
    fun getReposWithPageAsync(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ) : Deferred<Response<List<GitHubRepoEntity>>>

}