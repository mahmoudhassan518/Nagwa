package com.mahmoud.nagwa.data.repositories

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.data.datasources.locale.FileManager
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable
import javax.inject.Inject

open class FileManagerRepository @Inject constructor(
    private val fileManager: FileManager

){
    fun requestDownloadFile(item: Movie): Observable<LiveData<DownloadData>> {
        return  Observable.just(fileManager.downloadFile(item))
    }

}