package com.api.stockman.controller

import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.api.GenericMapperAPI
import com.api.stockman.model.Session
import com.api.stockman.model.User
import com.api.stockman.service.api.SessionsServiceAPI
import com.api.stockman.service.api.UsersServiceAPI
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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
    lateinit var sessionsService: SessionsServiceAPI

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
    fun insertUser(@RequestBody userDTO: UserDTO, response: HttpServletResponse) : ResponseEntity<Any> {

        // Si el usuario existe, devuelvo codigo estado 409 Conflict
        usersService[userDTO.email] ?: ResponseEntity<Any>("Usuario ya existente", HttpStatus.CONFLICT)

        // Si el usuario no está, se procede con la inserción del mismo
        val userEntity: User = userMapper.toEntity(userDTO)

        // Al insertar un usuario, también generamos una sesión para el mismo
        val session: Session = sessionsService.generateSessionForUser(userEntity)

        // Aniado la sesión al usuario
        userEntity.session = session
        usersService.insertOne(userEntity)
        
        //La sesión que hemos generado la introducimos en una cookie que viajará del cliente al servidor y viceversa
        val cookie: Cookie = Cookie("sessionID", session.sessionID)
        response.addCookie(cookie)

        return ResponseEntity<Any>(userMapper.toDTO(userEntity), HttpStatus.OK)
    }

    /**
     * ### DELETE USER
     * Función para eliminar un [User] del sistema.
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
        val userBD = usersService[email]

        // Si no lo encuentra, entonces retorno 404 not found
        userBD?: return ResponseEntity<String>("Usuario No Encontrado", HttpStatus.NOT_FOUND)

        usersService.deleteOne(email)
        return ResponseEntity<String>("Eliminado", HttpStatus.OK)
    }
}