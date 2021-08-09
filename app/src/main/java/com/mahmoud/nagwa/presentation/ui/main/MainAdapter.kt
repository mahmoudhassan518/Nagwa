package com.mahmoud.nagwa.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.nagwa.databinding.ItemMovieBinding
import com.mahmoud.nagwa.domain.models.Movie

import javax.inject.Inject

class MainAdapter @Inject constructor(
    private val itemDownloadClicked: ((item : Movie) -> Unit)
) :
    ListAdapter<Movie, MainAdapter.PersonHolder>(
        PopularPersonsDiffUtil
    ) {

    private object PopularPersonsDiffUtil :
        DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ) = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        return PersonHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    inner class PersonHolder(private val bind: ItemMovieBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bindViews(item: Movie) {
            bind.item = item

            bind.downloadFileButton.setOnClickListener {
                itemDownloadClicked.invoke(item)
            }
        }
    }
}
