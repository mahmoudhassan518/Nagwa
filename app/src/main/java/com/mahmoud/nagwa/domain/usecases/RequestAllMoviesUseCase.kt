package com.mahmoud.nagwa.domain.usecases

import com.mahmoud.nagwa.core.UseCase
import com.mahmoud.nagwa.data.repositories.MoviesRepository
import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable
import javax.inject.Inject

class RequestAllMoviesUseCase @Inject constructor(private val repository: MoviesRepository) :
    UseCase.NoParam<Observable<List<Movie>>> {
    override fun invoke(): Observable<List<Movie>> =
        repository.requestAllMovies()
}