package com.mahmoud.nagwa.di.modules.activity

import com.mahmoud.nagwa.data.datasources.locale.OfflineDataProvider
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.data.mappers.MovieItemDtoMapper
import com.mahmoud.nagwa.data.others.FileManager
import com.mahmoud.nagwa.data.repositories.FileManagerRepositoryImpl
import com.mahmoud.nagwa.data.repositories.MoviesRepositoryImpl
import com.mahmoud.nagwa.domain.repositories.FileManagerRepository
import com.mahmoud.nagwa.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun movieRepository(
        apiService: ApiService,
        offlineDataProvider: OfflineDataProvider,
        mapper: MovieItemDtoMapper,
    ): MoviesRepository =
        MoviesRepositoryImpl(
            apiService = apiService,
            offlineDataProvider = offlineDataProvider,
            mapper = mapper,
        )

    @Singleton
    @Provides
    fun fileManagerRepository(
        fileManager: FileManager
    ): FileManagerRepository =
        FileManagerRepositoryImpl(
            fileManager = fileManager
        )

}