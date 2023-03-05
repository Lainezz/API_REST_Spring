package com.api.stockman.dto.mapper.api

import com.api.stockman.dto.ProductoDTO
import com.api.stockman.model.Producto

interface GenericMapperAPI<T,E> {

    fun toEntity(dto: T): E
    fun toDTO(entity: E): T
    fun toListOfDTO(listOfEntities: MutableList<E>?) : MutableList<T>
    fun updateEntity(dto: T, entity: E)
}