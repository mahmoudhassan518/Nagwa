package com.mahmoud.nagwa.di.modules.application

import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.data.others.baseURL
import com.mahmoud.nagwa.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule::class])
class ApiInterFaceModule {
    @Provides
    @ApplicationScope
    fun getApiInterface(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @ApplicationScope
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun gsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun getClientRx(
        okHttpClient: OkHttpClient?,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient!!)
            .build()
    }

}