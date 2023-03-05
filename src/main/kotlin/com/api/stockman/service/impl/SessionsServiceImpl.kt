package com.api.stockman.service.impl

import com.api.stockman.commons.impl.GenericServiceImpl
import com.api.stockman.model.Session
import com.api.stockman.model.User
import com.api.stockman.service.api.SessionsServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SessionsServiceImpl : SessionsServiceAPI, GenericServiceImpl<Session, String>() {

    @Autowired
    lateinit var sessionsRepository: CrudRepository<Session, String>

    override fun generateSessionForUser(user: User) : Session {
        // 1º Buscamos la session del usuario
        val session: Session? = get(user.email)

        // 2º Si ya existe una sesión, devuelvo esa sesión
        if (session != null) return session

        // 3º Si no existe una sesión, se crea y se devuelve
        return Session(
            UUID.randomUUID().toString(),
            user
        )

    }

    override val dao: CrudRepository<Session, String>
        get() = sessionsRepository
}