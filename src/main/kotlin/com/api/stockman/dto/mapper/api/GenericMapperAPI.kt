package com.api.stockman.dto.mapper.api

import com.api.stockman.model.Producto

interface GenericMapperAPI<T,K> {

    fun toEntity(dto: T): K
    fun toDTO(entity: K): T
    fun toListOfDTO(listOfEntities: MutableList<K>?) : MutableList<T>
}