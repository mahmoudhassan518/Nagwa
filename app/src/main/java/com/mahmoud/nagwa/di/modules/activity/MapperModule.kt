package com.mahmoud.nagwa.di.modules.activity

import com.mahmoud.nagwa.data.mappers.MovieItemDtoMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    fun movieItemDtoMapper(
    ): MovieItemDtoMapper = MovieItemDtoMapper()

}