package com.mahmoud.nagwa.di.components.application

import com.mahmoud.nagwa.core.BaseApplication
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.di.modules.activity.MapperModule
import com.mahmoud.nagwa.di.modules.activity.RepositoryModule
import com.mahmoud.nagwa.di.modules.activity.UseCaseModule
import com.mahmoud.nagwa.di.modules.application.ApiInterFaceModule
import com.mahmoud.nagwa.di.modules.application.ApplicationContextModule
import com.mahmoud.nagwa.di.modules.application.NetworkModule
import com.mahmoud.nagwa.domain.usecases.RequestAllMoviesUseCase
import com.mahmoud.nagwa.domain.usecases.RequestDownloadFileUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationContextModule::class,
        NetworkModule::class,
        ApiInterFaceModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        MapperModule::class
    ]
)
interface ApplicationComponent {

    fun getApiInterface(): ApiService

    fun requestAllMoviesUseCase(): RequestAllMoviesUseCase
    fun requestDownloadFileUseCase(): RequestDownloadFileUseCase


    fun injectApplication(application: BaseApplication)
}