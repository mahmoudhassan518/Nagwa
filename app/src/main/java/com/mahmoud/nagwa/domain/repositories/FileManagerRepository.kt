package com.mahmoud.nagwa.domain.repositories

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie
import io.reactivex.Observable

interface FileManagerRepository {

    fun requestDownloadFile(item: Movie): Observable<LiveData<DownloadData>>

}