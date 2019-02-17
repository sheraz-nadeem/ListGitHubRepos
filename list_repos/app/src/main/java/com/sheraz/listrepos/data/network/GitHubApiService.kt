package com.sheraz.listrepos.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sheraz.listrepos.data.db.entity.GitHubRepoEntity
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("repos")
    fun getReposWithPageAsync(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ) : Deferred<List<GitHubRepoEntity>>

    companion object {

        operator fun invoke(): GitHubApiService {

            // Just to show off that Authorization header can be added
            // using an Interceptor
//            val requestInterceptor = Interceptor { chain ->
//
//                val original = chain.request()
//                val newRequest = original.newBuilder()
//                    .addHeader("Authorization", "AUTH_TOKEN")
//                    .build()
//
//                return@Interceptor chain.proceed(newRequest)
//            }

            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.github.com/users/xing/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubApiService::class.java)
        }
    }
}