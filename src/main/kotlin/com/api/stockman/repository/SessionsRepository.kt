package com.api.stockman.repository

import com.api.stockman.model.Session
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionsRepository : CrudRepository<Session, String> {
}