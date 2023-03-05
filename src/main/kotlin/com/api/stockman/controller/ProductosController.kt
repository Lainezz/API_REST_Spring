package com.api.stockman.controller

import com.api.stockman.dto.mapper.impl.ProductoMapper
import com.api.stockman.dto.ProductoDTO
import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.api.GenericMapperAPI
import com.api.stockman.model.Producto
import com.api.stockman.model.User
import com.api.stockman.service.api.ProductosServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
     * @see [link](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring#field-based-dependency-injection)
     */
    @Autowired
    lateinit var productosService: ProductosServiceAPI

    @Autowired
    lateinit var productoMapper: GenericMapperAPI<ProductoDTO, Producto>


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
    fun getAll(): ResponseEntity<MutableList<ProductoDTO>> {
        // Generamos una lista de DTOs
        val listaProductos: MutableList<ProductoDTO> = productoMapper.toListOfDTO(productosService.all)

        // Devolvemos la lista generada
        return ResponseEntity<MutableList<ProductoDTO>>(listaProductos, HttpStatus.OK)
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
    fun getOneProduct(@PathVariable id: Long) /*:ResponseEntity<ProductoDTO>*/{

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
    fun deleteOneProduct(@PathVariable id: Long) /*: ResponseEntity<String>*/ {

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
    fun updateOneProduct(@RequestBody productoDTO: ProductoDTO, @PathVariable id: Long) /*:ResponseEntity<ProductoDTO>*/{

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
    fun insertOne(@RequestBody productoDTO: ProductoDTO): ResponseEntity<ProductoDTO> {

        // Busco si el producto ya se encuentra en la base de datos
        val productoBD: Producto? = productosService.all?.find { producto: Producto -> producto.name == productoDTO.name }
        if(productoBD != null)
            return ResponseEntity<ProductoDTO>(productoDTO, HttpStatus.CONFLICT)

        // Si el producto no está en la BDD, convertimos el DTO a entidad
        val productoEntity: Producto = productoMapper.toEntity(productoDTO)

        // Guardamos la entidad en la BDD
        productosService.insertOne(productoEntity)

        // Devolvemos un mensaje correcto
        return ResponseEntity<ProductoDTO>(productoDTO, HttpStatus.OK)
    }


}