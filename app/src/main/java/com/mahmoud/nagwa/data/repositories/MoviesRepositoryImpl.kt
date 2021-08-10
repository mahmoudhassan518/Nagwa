package com.mahmoud.nagwa.data.repositories

import com.mahmoud.nagwa.data.datasources.locale.OfflineDataProvider
import com.mahmoud.nagwa.data.datasources.remote.ApiService
import com.mahmoud.nagwa.data.mappers.MovieItemDtoMapper
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.domain.repositories.MoviesRepository
import io.reactivex.Observable


open class MoviesRepositoryImpl constructor(
    val apiService: ApiService,
    private val offlineDataProvider: OfflineDataProvider,
    private val mapper: MovieItemDtoMapper
) : MoviesRepository {

    override fun requestAllMovies(): Observable<List<Movie>> =
        Observable.just(mapper.mapToDomainList(offlineDataProvider.moviesDto))

}