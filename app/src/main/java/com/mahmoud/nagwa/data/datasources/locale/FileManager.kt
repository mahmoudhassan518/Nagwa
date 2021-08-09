package com.mahmoud.nagwa.data.datasources.locale

import android.os.Build
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.DownloadListener
import com.mahmoud.nagwa.domain.models.DownloadData
import com.mahmoud.nagwa.domain.models.DownloadState
import com.mahmoud.nagwa.domain.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.inject.Inject

class FileManager @Inject constructor() {


    private val _downloadStateFlow = MutableLiveData(
        DownloadData(DownloadState.IDLE, "", 0, 0)
    )


    private var file = File(Environment.getExternalStorageDirectory().absolutePath + "/nagwa2")

    private fun createFile() {
        if (!file.isFile) {
            if (!(file.isDirectory)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        Files.createDirectory(Paths.get(file.absolutePath))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    file.mkdir()
                }
            }
        }
    }


    fun downloadFile(item: Movie): LiveData<DownloadData> {
        createFile()
        AndroidNetworking.download(item.url, file.path, item.fileName)
            .setTag("downloadTest")
            .setPriority(Priority.MEDIUM)
            .build()
            .setDownloadProgressListener { bytesDownloaded, totalBytes ->
                _downloadStateFlow.value =
                    getDownloadData(DownloadState.LOADING, totalBytes, bytesDownloaded)
            }
            .startDownload(object : DownloadListener {
                override fun onDownloadComplete() {
                    // do anything after completion
                    Timber.d("onDownloadComplete")
                    _downloadStateFlow.value = getDownloadData(DownloadState.SUCCESS, 0, 0)
                }

                override fun onError(error: ANError?) {
                    _downloadStateFlow.value = getDownloadData(DownloadState.ERROR, 0, 0)
                }
            })
        return _downloadStateFlow
    }


    private fun getDownloadData(sate: DownloadState, total: Long, current: Long): DownloadData {
        return when (sate) {
            DownloadState.ERROR -> DownloadData(
                downloadState = sate,
                message = "Downloading Field"
            )
            DownloadState.SUCCESS -> DownloadData(
                downloadState = sate,
                message = "Downloading Complete"
            )
            DownloadState.LOADING -> DownloadData(
                downloadState = sate,
                message = "Downloading ${getProgressDisplayLine(current, total)}",
                progress = getProgress(current, total)
            )

            DownloadState.IDLE -> DownloadData(
                downloadState = sate,
                message = "No Downloads"
            )
        }
    }

    private fun getProgress(currentBytes: Long, totalBytes: Long): Int {
        return ((getBytesToMBInt(currentBytes) / getBytesToMBInt(totalBytes)) * 100).toInt()
    }

    private fun getProgressDisplayLine(currentBytes: Long, totalBytes: Long): String {
        return getBytesToMBString(currentBytes).toString() + "/" + getBytesToMBString(totalBytes)
    }

    private fun getBytesToMBString(bytes: Long): String? {
        return java.lang.String.format(Locale.ENGLISH, "%.2fMb", getBytesToMBInt(bytes))
    }

    private fun getBytesToMBInt(bytes: Long): Double {
        return bytes / (1024.00 * 1024.00)
    }


}