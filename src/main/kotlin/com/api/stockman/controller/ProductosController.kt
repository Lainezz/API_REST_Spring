package com.api.stockman.controller

import com.api.stockman.dto.ProductoDTO
import com.api.stockman.dto.mapper.impl.ProductoMapper
import com.api.stockman.model.Producto
import com.api.stockman.model.Session
import com.api.stockman.service.api.ProductosServiceAPI
import com.api.stockman.service.api.SessionsServiceAPI
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * ### Controlador de Productos
 * Controlador de la API para el recurso de productos.
 * - ENTRYPOINT: /api/v1/productos
 *
 * @see [com.api.stockman.model.Producto]
 * @since v1.0
 * @author Diego
 */
@RestController
@RequestMapping("/api/v1/productos")
@CrossOrigin("*")
class ProductosController {

    /**
     * Inyección de dependencia del servicio para productos
     * @see ProductosServiceAPI
     * @see link(https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring#field-based-dependency-injection)
     */
    @Autowired
    lateinit var productosService: ProductosServiceAPI
    @Autowired
    lateinit var sessionsService: SessionsServiceAPI

    @Autowired
    lateinit var productoMapper: ProductoMapper

    /**
     * ### GET ALL PRODUCT
     * Función para obtener una lista de [Producto] del sistema.
     *
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/productos/
     *
     * @return objeto de tipo [ResponseEntity] con objeto de tipo [MutableList] de [ProductoDTO]
     */
    @GetMapping("/")
    fun getAll(): ResponseEntity<Any> {
        // Generamos una lista de DTOs
        val listaProductos: MutableList<ProductoDTO> = productoMapper.toListOfDTO(productosService.all)

        // Devolvemos la lista generada
        return ResponseEntity<Any>(listaProductos, HttpStatus.OK)
    }

    /**
     * ### GET ONE PRODUCT
     * Función para obtener un [Producto] del sistema.
     * Recibe el id del producto a actualizar en la ruta de la petición
     *
     * - HTTP method: GET
     * - ENDPOINT: /api/v1/productos/{id}
     *
     * @param id: [Long]
     * @return objeto de tipo [ResponseEntity] con objeto de tipo [ProductoDTO]
     */
    @GetMapping("/{id}")
    fun getOneProduct(@PathVariable id: Long) : ResponseEntity<Any>{
        // Consigo el producto de la BD
        val productBD: Producto? = productosService[id]

        // Si no lo encuentra, entonces retorno 404 not found
        productBD?: return ResponseEntity<Any>("Producto No Encontrado", HttpStatus.NOT_FOUND)

        return  ResponseEntity<Any>(productoMapper.toDTO(productBD), HttpStatus.OK)
    }

    /**
     * ### DELETE PRODUCT
     * Función para eliminar un [Producto] del sistema.
     * Recibe el id del producto a actualizar en la ruta de la petición
     *
     * - HTTP method: DELETE
     * - ENDPOINT: /api/v1/productos/{id}
     *
     * @param id: [Long]
     * @return objeto de tipo [ResponseEntity] con un [String] que contendrá un mensaje
     */
    @DeleteMapping("/{id}")
    fun deleteOneProduct(@PathVariable id: Long, request: HttpServletRequest) : ResponseEntity<Any> {

        if(!checkCookie(request)) return ResponseEntity<Any>("No autorizado", HttpStatus.FORBIDDEN)

        // Consigo el producto de la BD
        val productBD: Producto? = productosService[id]

        // Si no lo encuentra, entonces retorno 404 not found
        productBD?: return ResponseEntity<Any>("Producto No Encontrado", HttpStatus.NOT_FOUND)

        productosService.deleteOne(id)
        return ResponseEntity<Any>("Eliminado", HttpStatus.OK)
    }

    /**
     * ### UPDATE PRODUCT
     * Función para actualizar un [Producto] en el sistema.
     * Recibe el id del producto a actualizar en la ruta de la petición.
     * Recibe los datos del nuevo producto en el cuerpo de la petición.
     *
     * - HTTP method: PUT
     * - ENDPOINT: /api/v1/productos/{id}
     *
     * @param productoDTO: [ProductoDTO]
     * @param id: [Long]
     * @return objeto de tipo [ResponseEntity] con objeto de tipo [ProductoDTO]
     */
    @PutMapping("/{id}")
    fun updateOneProduct(@RequestBody productoDTO: ProductoDTO, @PathVariable id: Long, request: HttpServletRequest) :ResponseEntity<Any>{

        if(!checkCookie(request)) return ResponseEntity<Any>("No autorizado", HttpStatus.FORBIDDEN)

        // Consigo el producto de la BD
        val productBD: Producto? = productosService[id]

        // Si no lo encuentra, entonces retorno 404 not found
        productBD?: return ResponseEntity<Any>("Producto No Encontrado", HttpStatus.NOT_FOUND)

        productoMapper.updateEntity(productoDTO, productBD)
        productosService.insertOne(productBD)
        return  ResponseEntity<Any>(productoMapper.toDTO(productBD), HttpStatus.OK)
    }


    /**
     * Función para insertar un nuevo [Producto] en el sistema.
     * Recibe los datos del nuevo producto en el cuerpo de la petición
     *
     * HTTP method: POST
     * ENDPOINT: /api/v1/productos/
     *
     * @param productoDTO: [ProductoDTO]
     * @return objeto de tipo [ResponseEntity] con objeto de tipo [ProductoDTO]
     */
    @PostMapping("/")
    fun insertOne(@RequestBody productoDTO: ProductoDTO, request: HttpServletRequest): ResponseEntity<Any> {

        // Compruebo la cookie
        if(!checkCookie(request)) return ResponseEntity<Any>("No autorizado", HttpStatus.FORBIDDEN)

        // Busco si el producto ya se encuentra en la base de datos
        val productoBD: Producto? = productosService.all?.find { producto: Producto -> producto.name == productoDTO.name }
        if(productoBD != null)
            return ResponseEntity<Any>(productoDTO, HttpStatus.CONFLICT)

        // Si el producto no está en la BDD, convertimos el DTO a entidad
        val productoEntity: Producto = productoMapper.toEntity(productoDTO)

        // Guardamos la entidad en la BDD
        productosService.insertOne(productoEntity)

        // Devolvemos un mensaje correcto
        return ResponseEntity<Any>(productoDTO, HttpStatus.OK)
    }

    /**
     * Función para comprobar la valides de una coookie
     */
    fun checkCookie(request: HttpServletRequest): Boolean {

        println("Cookie: ${request.cookies.find { cookie: Cookie? -> cookie?.name == "sessionID" }?.value}")
        // Busco que la cookie de la petición contenga una sessionID
        val sessionID = request.cookies.find { cookie: Cookie? -> cookie?.name == "sessionID" }?.value
        // Si no hay sessionID en la petición, es que nunca ha habido un logueo y por tanto, se deniega la entrada
        sessionID?: return false

        // Si existe una sessionID, compruebo que esa sessionID se encuentre en la BDD
        val session: Session? = sessionsService[sessionID]
        session?: return false
        return true
    }
}