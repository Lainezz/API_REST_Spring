package com.api.stockman.repository

import com.api.stockman.model.Producto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductosRepository : CrudRepository<Producto, Long>

/*
https://www.concretepage.com/spring-5/spring-data-crudrepository-example#CrudRepository

CrudRepository no hace falta que sea implementada, dicha implementación la da Spring
en tiempo de ejecución automáticamente.
 */