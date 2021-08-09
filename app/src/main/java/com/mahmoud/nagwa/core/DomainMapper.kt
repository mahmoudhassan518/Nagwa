package com.mahmoud.nagwa.core

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(t: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T?

}