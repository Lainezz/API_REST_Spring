package com.api.stockman.service.impl

import com.api.stockman.commons.impl.GenericServiceImpl
import com.api.stockman.model.User
import com.api.stockman.repository.UsersRepository
import com.api.stockman.service.api.UsersServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl : UsersServiceAPI, GenericServiceImpl<User, String>() {

    @Autowired
    lateinit var usersRepository: UsersRepository

    override val dao: CrudRepository<User, String>
        get() = usersRepository
}