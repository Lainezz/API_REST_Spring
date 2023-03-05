package com.api.stockman.dto.mapper.impl

import com.api.stockman.dto.mapper.api.GenericMapperAPI
import com.api.stockman.dto.UserDTO
import com.api.stockman.model.User
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserMapper : GenericMapperAPI<UserDTO, User>{

    @Autowired
    lateinit var modelMapper: ModelMapper

    override fun toDTO(entity: User): UserDTO {
        return UserDTO(
            nombre = entity.nombre,
            email = entity.email,
            password = entity.password
        )
    }

    override fun toEntity(dto: UserDTO): User {
        val userEntity: User = modelMapper.map(dto, User::class.java)
        userEntity.entryDate = LocalDate.now()
        userEntity.banned = false
        return userEntity
    }
}