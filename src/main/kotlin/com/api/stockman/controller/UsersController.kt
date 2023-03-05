package com.api.stockman.controller

import com.api.stockman.dto.ProductoDTO
import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.api.GenericMapperAPI
import com.api.stockman.dto.mapper.impl.UserMapper
import com.api.stockman.model.Producto
import com.api.stockman.model.User
import com.api.stockman.service.api.ProductosServiceAPI
import com.api.stockman.service.api.UsersServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


/**
 * ### Controlador de USERS
 * Controlador de la API para el recurso de users.
 * - ENTRYPOINT: /api/v1/productos
 *
 * @see [com.api.stockman.model.User]
 * @since v1.0
 * @author Diego
 */
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
class UsersController {

    /**
     * Inyección de dependencia del servicio para users
     * @see UsersServiceAPI
     * @see [link](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring#field-based-dependency-injection)
     */
    @Autowired
    lateinit var usersService: UsersServiceAPI

    @Autowired
    lateinit var userMapper: GenericMapperAPI<UserDTO, User>

    /**
     * ### INSERT USER
     * Función para insertar un nuevo [User] en el sistema.
     * Recibe los datos del nuevo usuario en el cuerpo de la petición.
     *
     * - HTTP method: POST
     * - ENDPOINT: /api/v1/users/
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

    /**
     * ### UPDATE USER
     * Función para actualizar un [User] en el sistema.
     * Recibe el email del usuario a actualizar en la ruta de la petición
     *
     * - HTTP method: DELETE
     * - ENDPOINT: /api/v1/users/{email}
     *
     * @param email: [String]
     * @return objeto de tipo [ResponseEntity] con un [String] que contendrá un mensaje
     */
    @DeleteMapping("/{email}")
    fun deleteUser(@PathVariable email: String) : ResponseEntity<String> {
        // Consigo el usuario de la BD
        val userBD = usersService.get(email)

        // Si no lo encuentra, entonces retorno 404 not found
        userBD?: return ResponseEntity<String>("Usuario No Encontrado", HttpStatus.NOT_FOUND)

        usersService.deleteOne(email)
        return ResponseEntity<String>("Eliminado", HttpStatus.OK)
    }
}