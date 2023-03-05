package com.api.stockman.repository

import com.api.stockman.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : CrudRepository<User, Long> {
}