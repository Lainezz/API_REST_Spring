package com.api.stockman.controller

import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.impl.UserMapper
import com.api.stockman.model.User
import com.api.stockman.service.api.UsersServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Diego
 * @since 1.0
 * Controlador de la API para el recurso de users [com.api.stockman.model.User]
 * EntryPoint: "/api/v1/productos"
 * @see [com.api.stockman.model.Producto]
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
class UsersController {

    @Autowired
    lateinit var usersService: UsersServiceAPI

    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * Función para insertar un nuevo usuario en el sistema.
     * Recibe los datos del nuevo usario en el cuerpo de la petición
     *
     * HTTP method: POST
     * ENDPOINT: /api/v1/users/
     *
     * @param userDTO: [UserDTO]
     * @return objeto de tipo [ResponseEntity] con objeto de tipo [UserDTO]
     */
    @PostMapping("/")
    fun insertUser(@RequestBody userDTO: UserDTO) : ResponseEntity<User> {
        val userEntity: User = userMapper.toEntity(userDTO)

        usersService.insertOne(userEntity)

        return ResponseEntity<User>(userEntity, HttpStatus.OK)
    }

    fun deleteUser(){

    }
}