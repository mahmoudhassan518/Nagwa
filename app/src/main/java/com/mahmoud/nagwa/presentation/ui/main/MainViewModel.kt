package com.mahmoud.nagwa.presentation.ui.main

import com.mahmoud.nagwa.core.BaseViewModel
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.domain.usecases.RequestAllMoviesUseCase
import com.mahmoud.nagwa.domain.usecases.RequestDownloadFileUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    disposables: CompositeDisposable,
    private val requestAllMoviesUseCase: RequestAllMoviesUseCase,
    private val requestDownloadFileUseCase: RequestDownloadFileUseCase
) :
    BaseViewModel(disposables) {

    fun requestAllMovies() {
        compositeDisposable.add(
            Observable.just("")
                .doOnNext { onLoading() }
                .observeOn(Schedulers.io())
                .flatMap { requestAllMoviesUseCase.invoke() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    internalState.value = MainActivityViewModelState.MoviesListResult(it)
                })
                { throwable: Throwable -> onError(throwable) })
    }

    fun requestDownloadFile(item: Movie) {
        compositeDisposable.add(
            Observable.just(item)
                .observeOn(Schedulers.io())
                .flatMap { requestDownloadFileUseCase.invoke(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    internalState.value = MainActivityViewModelState.DownloadingResult(it)
                })
                { throwable: Throwable -> onError(throwable) })
    }

}