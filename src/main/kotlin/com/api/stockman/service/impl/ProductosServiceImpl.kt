package com.api.stockman.service.impl

import com.api.stockman.commons.impl.GenericServiceImpl
import com.api.stockman.model.Producto
import com.api.stockman.repository.ProductosRepository
import com.api.stockman.service.api.ProductosServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class ProductosServiceImpl : GenericServiceImpl<Producto, Long>(), ProductosServiceAPI {

    @Autowired
    lateinit var productosRepository: ProductosRepository

    override val dao: CrudRepository<Producto, Long>
        get() = productosRepository

}