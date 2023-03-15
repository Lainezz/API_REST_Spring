package com.api.stockman.dto.mapper.impl

import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.api.GenericMapperAPI
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
        // Mapeo los atributos básicos del usuario
        val userEntity: User = modelMapper.map(dto, User::class.java)
        // Aniado el entryDate y si está baneado o no
        userEntity.entryDate = LocalDate.now()
        userEntity.banned = false

        return userEntity
    }

    override fun toListOfDTO(listOfEntities: MutableList<User>?): MutableList<UserDTO> {
        val returnList: MutableList<UserDTO> = mutableListOf()
        listOfEntities?.forEach { entry: User -> returnList.add(toDTO(entry)) }
        return returnList
    }

    override fun updateEntity(dto: UserDTO, entity: User) {
        modelMapper.map(dto, entity)
    }
}