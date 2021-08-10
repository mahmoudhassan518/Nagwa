package com.mahmoud.nagwa.data.repositories

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.data.others.FileManager
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.domain.repositories.FileManagerRepository
import io.reactivex.Observable

open class FileManagerRepositoryImpl(
    private val fileManager: FileManager

) : FileManagerRepository {
    override fun requestDownloadFile(item: Movie): Observable<LiveData<DownloadData>> =
        Observable.just(fileManager.downloadFile(item))
}