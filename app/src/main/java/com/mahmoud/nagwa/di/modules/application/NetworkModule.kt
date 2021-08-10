package com.mahmoud.nagwa.di.modules.application

import android.content.Context
import com.google.gson.Gson
import com.mahmoud.nagwa.BuildConfig.DEBUG
import com.mahmoud.nagwa.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Singleton

@Module(includes = [ApplicationContextModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun cacheFile(@ApplicationContext context: Context): File = // Context is external decadence
        File(context.cacheDir, "okHttp_cache")


    @Provides
    @Singleton
    fun cache(file: File?): Cache =
        Cache(file!!, 10 * 1000 * 1000) //10MB cache


    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        if (DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }


    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}