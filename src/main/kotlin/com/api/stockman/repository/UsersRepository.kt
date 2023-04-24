package com.api.stockman.repository

import com.api.stockman.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : CrudRepository<User, String>

/*
https://www.concretepage.com/spring-5/spring-data-crudrepository-example#CrudRepository

CrudRepository no hace falta que sea implementada, dicha implementación la da Spring
en tiempo de ejecución automáticamente.
 */