package com.mahmoud.nagwa.domain.usecases

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.core.UseCase
import com.mahmoud.nagwa.data.repositories.FileManagerRepository
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable
import javax.inject.Inject


class RequestDownloadFileUseCase @Inject constructor(private val repository: FileManagerRepository) :
    UseCase.WithParam<Observable<LiveData<DownloadData>>, Movie> {
    override fun invoke(param: Movie): Observable<LiveData<DownloadData>> =
        repository.requestDownloadFile(param)
}