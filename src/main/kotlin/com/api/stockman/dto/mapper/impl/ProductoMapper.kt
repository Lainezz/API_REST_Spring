package com.api.stockman.dto.mapper.impl

import com.api.stockman.dto.mapper.api.GenericMapperAPI
import com.api.stockman.dto.ProductoDTO
import com.api.stockman.model.Producto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * Mapeador para la entidad [Producto]
 */
@Component
class ProductoMapper : GenericMapperAPI<ProductoDTO, Producto>{

    @Autowired
    lateinit var modelMapper: ModelMapper

    /**
     * Función que mapea un DTO [ProductoDTO] a entidad [Producto]
     * [Producto.entryDate] -> seteada a fecha de hoy
     * @param dto: [ProductoDTO]
     * @return objeto de tipo [Producto]
     */
    override fun toEntity(dto: ProductoDTO) : Producto {
        val productoEntity: Producto = modelMapper.map(dto, Producto::class.java)
        productoEntity.entryDate = LocalDate.now()
        productoEntity.modificationDate = LocalDate.now()
        return productoEntity
    }

    /**
     * Función para mapear una entidad [Producto] al formato [ProductoDTO]
     * Se usa para no exponer la fecha de alta [Producto.entryDate] ni el id del producto [Producto.id]
     * @param entity: Producto
     * @return objeto de tipo [ProductoDTO]
     */
    override fun toDTO(entity: Producto) : ProductoDTO {
        return ProductoDTO(
            name = entity.name,
            quantity = entity.quantity,
            price = entity.price
        )
    }

    /**
     * Función para mapear un alista de [Producto] a una lista de [ProductoDTO]
     * @param productosEntity: MutableList<[Producto]>
     * @return lista con objetos de tipo [ProductoDTO]
     */
    fun toListOfDTO(productosEntity: MutableList<Producto>?) : MutableList<ProductoDTO> {

        val returnList: MutableList<ProductoDTO> = mutableListOf()
        productosEntity?.forEach { entry: Producto -> returnList.add(toDTO(entry)) }
        return returnList
    }

}