package com.mahmoud.nagwa.di.modules.activity

import com.mahmoud.nagwa.domain.repositories.FileManagerRepository
import com.mahmoud.nagwa.domain.repositories.MoviesRepository
import com.mahmoud.nagwa.domain.usecases.RequestAllMoviesUseCase
import com.mahmoud.nagwa.domain.usecases.RequestDownloadFileUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun requestAllMoviesUseCase(
        repository: MoviesRepository,
    ): RequestAllMoviesUseCase =
        RequestAllMoviesUseCase(repository = repository)


    @Singleton
    @Provides
    fun requestDownloadFileUseCase(
        repository: FileManagerRepository,
    ): RequestDownloadFileUseCase =
        RequestDownloadFileUseCase(
            repository = repository,
        )

}