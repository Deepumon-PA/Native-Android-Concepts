package com.deepu.myandroidapp.common

interface DomainMapper<T, DomainModel> {

    fun mapToDomain(entityOrDto: T): DomainModel

    fun mapFromDomain(domainModel: DomainModel): T
}