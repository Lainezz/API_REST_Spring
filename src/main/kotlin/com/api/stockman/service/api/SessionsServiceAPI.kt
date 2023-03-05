package com.api.stockman.service.api

import com.api.stockman.commons.api.GenericServiceAPI
import com.api.stockman.model.Session
import com.api.stockman.model.User

interface SessionsServiceAPI : GenericServiceAPI<Session, String> {

    fun generateSessionForUser(user: User): Session
}