package com.sheraz.listrepos.di.module

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sheraz.listrepos.BuildConfig
import com.sheraz.listrepos.data.network.GitHubApiService
import com.sheraz.listrepos.data.network.GitHubNetworkDataSource
import com.sheraz.listrepos.data.network.GitHubNetworkDataSourceImpl
import com.sheraz.listrepos.data.network.HttpLogger
import com.sheraz.listrepos.utils.Logger
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providerHttpLogger(logger: Logger): HttpLogger = HttpLogger(logger)

    @Provides
    fun provideLoggingInterceptor(httpLogger: HttpLogger): HttpLoggingInterceptor {

        val httpLoggingInterceptor = HttpLoggingInterceptor(httpLogger)

        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        val cache = Cache(context.cacheDir, CACHE_SIZE)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader = OkHttp3Downloader(okHttpClient)

    @Provides
    @Singleton
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).downloader(okHttp3Downloader).build()
    }

    @Provides
    fun provideCallAdapterFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
                        gsonConverterFactory: GsonConverterFactory) : Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(logger: Logger, apiService: GitHubApiService) : GitHubNetworkDataSource {
        return GitHubNetworkDataSourceImpl(logger, apiService)
    }

    companion object {
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
        private const val BASE_URL = "https://api.github.com/users/xing/"
    }
}