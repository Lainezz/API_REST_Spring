package com.api.stockman.controller

import com.api.stockman.dto.producto.mapper.ProductoMapper
import com.api.stockman.dto.producto.GetAllProductoDTO
import com.api.stockman.dto.producto.InsertProductoDTO
import com.api.stockman.model.Producto
import com.api.stockman.service.api.ProductosServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Diego
 * @since 1.0
 * Controlador de la API para el recurso de productos
 * EntryPoint: "/api/v1/productos"
 * @see [com.api.stockman.model.Producto]
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
    lateinit var productoMapper: ProductoMapper


    @GetMapping("/")
    fun getAll(): ResponseEntity<MutableList<GetAllProductoDTO>> {

        // Generamos una lista de DTOs
        val listaProductos: MutableList<GetAllProductoDTO> = productoMapper.toListOfDTO(productosService.all)

        // Devolvemos la lista generada
        return ResponseEntity<MutableList<GetAllProductoDTO>>(listaProductos, HttpStatus.OK)
    }

    /**
     * Función que controla el endpoint "/api/v1/productos" para el método POST
     *
     * @param productoDTO: [InsertProductoDTO]
     */
    @PostMapping("/")
    fun insertOne(@RequestBody productoDTO: InsertProductoDTO): ResponseEntity<InsertProductoDTO> {

        // Convertimos el DTO a entidad
        val productoEntity: Producto = productoMapper.convertToEntity(productoDTO)

        // Guardamos la entidad en la BDD
        productosService.insertOne(productoEntity)

        // Devolvemos un mensaje correcto
        return ResponseEntity<InsertProductoDTO>(productoDTO, HttpStatus.OK)
    }


}