package com.api.stockman.repository

import com.api.stockman.model.Producto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductosRepository : CrudRepository<Producto, Long>