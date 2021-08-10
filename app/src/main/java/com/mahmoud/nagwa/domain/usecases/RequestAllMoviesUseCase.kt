package com.mahmoud.nagwa.domain.usecases

import com.mahmoud.nagwa.core.UseCase
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.domain.repositories.MoviesRepository
import io.reactivex.Observable

class RequestAllMoviesUseCase(val repository: MoviesRepository) :
    UseCase.NoParam<Observable<List<Movie>>> {
    override fun invoke(): Observable<List<Movie>> =
        repository.requestAllMovies()
}