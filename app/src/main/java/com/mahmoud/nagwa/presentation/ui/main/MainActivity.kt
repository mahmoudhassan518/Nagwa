package com.mahmoud.nagwa.presentation.ui.main

import android.Manifest
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud.nagwa.R
import com.mahmoud.nagwa.core.BaseActivity
import com.mahmoud.nagwa.core.ViewState
import com.mahmoud.nagwa.databinding.ActivityMainBinding
import com.mahmoud.nagwa.di.components.activity.MainControllerComponent
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.DownloadState
import com.mahmoud.nagwa.domain.models.Movie
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private lateinit var mainAdapter: MainAdapter

    @Inject
    override lateinit var viewModel: MainViewModel

    var isFileDownloading: Boolean = false
    var movie: Movie? = null

    private val askWriteStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result && movie != null && !isFileDownloading)
                viewModel.requestDownloadFile(movie!!)
        }

    override fun injectActivity(component: MainControllerComponent) {
        component.injectActivity(this)
    }

    override fun setup() {

        mainAdapter = MainAdapter { item ->
            this.movie = item
            askWriteStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
        prepareList()

        viewModel.requestAllMovies()
    }

    override fun render(state: ViewState) {
        when (state) {
            is MainActivityViewModelState.MoviesListResult -> onGetMoviesList(state.data)
            is MainActivityViewModelState.DownloadingResult -> onGetDownloadingResult(state.data)
        }
    }

    private fun onGetMoviesList(data: List<Movie>?) {
        mainAdapter.submitList(data)
    }

    private fun onGetDownloadingResult(data: LiveData<DownloadData>) {
        Timber.d("status $data")

        data.observe(
            this, {
                binder.messageTextView.text = it.message
                binder.downloadProgressProgressBar.progress = it.progress


                binder.downloadProgressProgressBar.visibility =
                    if (it.downloadState == DownloadState.LOADING) View.VISIBLE else View.GONE

                isFileDownloading = it.downloadState == DownloadState.LOADING
            }
        )

    }

    private fun prepareList() {
        binder.recyclerViewRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
    }
}