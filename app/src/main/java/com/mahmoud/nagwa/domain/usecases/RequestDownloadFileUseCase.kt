package com.mahmoud.nagwa.domain.usecases

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.core.UseCase
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.domain.repositories.FileManagerRepository
import io.reactivex.Observable


class RequestDownloadFileUseCase(private val repository: FileManagerRepository) :
    UseCase.WithParam<Observable<LiveData<DownloadData>>, Movie> {
    override fun invoke(param: Movie): Observable<LiveData<DownloadData>> =
        repository.requestDownloadFile(param)
}