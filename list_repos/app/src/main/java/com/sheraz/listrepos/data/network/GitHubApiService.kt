package com.sheraz.listrepos.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GitHubApiService {


    companion object {
        operator fun invoke(
        ): GitHubApiService {

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