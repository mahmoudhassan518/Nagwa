package com.mahmoud.nagwa.di.modules.application

import android.content.Context
import com.google.gson.Gson
import com.mahmoud.nagwa.BuildConfig.DEBUG
import com.mahmoud.nagwa.di.modules.application.ApplicationContextModule
import com.mahmoud.nagwa.di.qualifiers.ApplicationContext
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module(includes = [ApplicationContextModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun cacheFile(@ApplicationContext context: Context): File { // Context is external decadence
        return File(context.cacheDir, "okHttp_cache")
    }

    @Provides
    @ApplicationScope
    fun cache(file: File?): Cache {
        return Cache(file!!, 10 * 1000 * 1000) //10MB cache
    }

    @Provides
    @ApplicationScope
    fun okHttpClient( loggingInterceptor: HttpLoggingInterceptor,
                      cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }



    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }


    @ApplicationScope
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}