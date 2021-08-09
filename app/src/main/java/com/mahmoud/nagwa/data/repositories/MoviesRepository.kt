package com.mahmoud.nagwa.data.repositories

import com.mahmoud.nagwa.data.datasources.locale.OfflineDataProvider
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.data.mappers.MovieItemDtoMapper
import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable
import javax.inject.Inject

open class MoviesRepository @Inject constructor(

    val apiService: ApiService,
    val offlineDataProvider: OfflineDataProvider,
    val mapper: MovieItemDtoMapper
) {

    fun requestAllMovies(): Observable<List<Movie>> =
        Observable.just(mapper.mapToDomainList(offlineDataProvider.moviesDto))

}