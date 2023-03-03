package com.api.stockman.dto.producto.mapper

import com.api.stockman.dto.producto.GetAllProductoDTO
import com.api.stockman.dto.producto.InsertProductoDTO
import com.api.stockman.model.Producto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * Mapeador para la entidad [Producto]
 */
@Component
class ProductoMapper {

    @Autowired
    lateinit var modelMapper: ModelMapper

    /**
     * Función para mapear una entidad [Producto] al formato [GetAllProductoDTO]
     * Se usa para no exponer la fecha de alta [Producto.entryDate]
     * @param productoEntity: Producto
     * @return objeto de tipo [GetAllProductoDTO]
     */
    fun convertToGetAllDTO(productoEntity: Producto): GetAllProductoDTO {
        return modelMapper.map(productoEntity, GetAllProductoDTO::class.java)
    }

    /**
     * Función para mapear una entidad [Producto] al formato [InsertProductoDTO]
     * Se usa para no exponer la fecha de alta [Producto.entryDate] ni el id del producto [Producto.id]
     * @param productoEntity: Producto
     * @return objeto de tipo [InsertProductoDTO]
     */
    fun convertToInsertDTO(productoEntity: Producto) : InsertProductoDTO {
        return modelMapper.map(productoEntity, InsertProductoDTO::class.java)
    }

    /**
     * Función para mapear un alista de [Producto] a una lista de [GetAllProductoDTO]
     * @param productosEntity: MutableList<[Producto]>
     * @return lista con objetos de tipo [GetAllProductoDTO]
     */
    fun toListOfDTO(productosEntity: MutableList<Producto>?) : MutableList<GetAllProductoDTO> {
        val returnList: MutableList<GetAllProductoDTO> = mutableListOf()
        productosEntity?.forEach { entry: Producto -> returnList.add(convertToGetAllDTO(entry)) }
        return returnList
    }


    /**
     * Función que mapea un DTO [InsertProductoDTO] a entidad [Producto]
     * [Producto.entryDate] -> seteada a fecha de hoy
     * @param productoDTO: [InsertProductoDTO]
     * @return objeto de tipo [Producto]
     */
    fun convertToEntity(productoDTO: InsertProductoDTO) : Producto {
        var productoEntity: Producto = modelMapper.map(productoDTO, Producto::class.java)
        productoEntity.entryDate = LocalDate.now()
        return productoEntity
    }


}