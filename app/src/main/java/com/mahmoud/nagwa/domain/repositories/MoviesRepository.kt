package com.mahmoud.nagwa.domain.repositories

import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable

interface MoviesRepository {

    fun requestAllMovies(): Observable<List<Movie>>

}