package com.mahmoud.nagwa.data.mappers

import com.mahmoud.nagwa.core.DomainMapper
import com.mahmoud.nagwa.data.models.dto.MoviesDtoItem
import com.mahmoud.nagwa.domain.models.Movie
import javax.inject.Inject


class MovieItemDtoMapper  @Inject constructor() : DomainMapper<MoviesDtoItem, Movie> {
    override fun mapToDomainModel(t: MoviesDtoItem): Movie =
        Movie(
            id = t.id,
            name = t.name,
            type = t.type,
            url = t.url,
            fileName = getFileName(t)
        )

    override fun mapFromDomainModel(domainModel: Movie): MoviesDtoItem =
        MoviesDtoItem(
            id = domainModel.id,
            name = domainModel.name,
            type = domainModel.type,
            url = domainModel.url
        )


    fun mapToDomainList(list: List<MoviesDtoItem>): List<Movie> =
        list.map { mapToDomainModel(it) }


    fun mapFromDomainList(list: List<Movie>): List<MoviesDtoItem> =
        list.map { mapFromDomainModel(it) }


    private fun getFileName(t: MoviesDtoItem): String {
        val bits: List<String> = t.url.split("/")
        return if(bits.isNullOrEmpty()) "empty" else  bits[bits.size - 1]
    }


}


