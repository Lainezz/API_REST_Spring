package com.api.stockman.dto.mapper.api

interface GenericMapperAPI<T,K> {

    fun toEntity(dto: T): K
    fun toDTO(entity: K): T
}