package com.mahmoud.nagwa.presentation.ui.main

import androidx.lifecycle.LiveData
import com.mahmoud.nagwa.core.ViewState
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.Movie

sealed class MainActivityViewModelState : ViewState() {
    data class MoviesListResult(val data: List<Movie>) : Loaded<List<Movie>>(data)
    data class DownloadingResult(val data: LiveData<DownloadData>) :
        Loaded<LiveData<DownloadData>>(data)

}