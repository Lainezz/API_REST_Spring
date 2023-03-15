package com.api.stockman.dto.mapper.api

interface GenericMapperAPI<T,E> {

    fun toEntity(dto: T): E
    fun toDTO(entity: E): T
    fun toListOfDTO(listOfEntities: MutableList<E>?) : MutableList<T>
    fun updateEntity(dto: T, entity: E)
}