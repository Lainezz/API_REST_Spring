package com.api.stockman

import com.api.stockman.controller.UsersController
import com.api.stockman.dto.UserDTO
import com.api.stockman.dto.mapper.impl.UserMapper
import com.api.stockman.model.Session
import com.api.stockman.model.User
import com.api.stockman.repository.SessionsRepository
import com.api.stockman.repository.UsersRepository
import com.api.stockman.service.api.SessionsServiceAPI
import com.api.stockman.service.api.UsersServiceAPI
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.delete
import java.time.LocalDate
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var sessionRepository: SessionsRepository

    @BeforeEach
    fun setup() {

        // Insertar un usuario de prueba
        usersRepository.save(
            User(
                nombre = "Test User",
                email = "test@example.com",
                password = "password123",
                entryDate = LocalDate.now(),
                banned = false,
                session = null
            )
        )
    }

    @AfterAll
    fun cleanupAfterAllTests() {
        val emailAEliminar = "bob@example.com"
        usersRepository.deleteById(emailAEliminar)
        sessionRepository.deleteById(emailAEliminar)
    }

    @Test
    fun `insertar usuario nuevo devuelve 200 y cookie con sessionID`() {
        val jsonRequest = """
            {
                "nombre": "Bob",
                "email": "bob@example.com",
                "password": "hashed_password_2"
            }
        """.trimIndent()

        val result = mockMvc.perform(
            post("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
            .andExpect(status().isOk)
            .andExpect(cookie().exists("sessionID"))
            .andReturn()

        val sessionId = result.response.getCookie("sessionID")?.value

        println("✅ SessionID generado: $sessionId")
        assert(sessionId != null && sessionId.matches(Regex("[a-f0-9\\-]{36}"))) {
            "La cookie no contiene un UUID válido"
        }
    }

    @Test
    fun `login usuario nuevo devuelve 200 y cookie con sessionID`() {
        val jsonRequest = """
            {
                "nombre": "Alice",
                "email": "alice@example.com",
                "password": "hashed_password_1"
            }
        """.trimIndent()

        val result = mockMvc.perform(
            post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
            .andExpect(status().isOk)
            .andExpect(cookie().exists("sessionID"))
            .andReturn()

        val sessionId = result.response.getCookie("sessionID")?.value

        println("✅ SessionID generado: $sessionId")
        assert(sessionId != null && sessionId.matches(Regex("[a-f0-9\\-]{36}"))) {
            "La cookie no contiene un UUID válido"
        }
    }

    @Test
    fun `DELETE usuario existente devuelve 200 OK`() {
        mockMvc.delete("/api/v1/users/test@example.com")
            .andExpect {
                status { isOk() }
                content { string("Eliminado") }
            }
    }

    @Test
    fun `DELETE usuario no existente devuelve 404 Not Found`() {
        mockMvc.delete("/api/v1/users/noexiste@example.com")
            .andExpect {
                status { isNotFound() }
                content { string("Usuario No Encontrado") }
            }
    }

    @Test
    fun `mockMvc should not be null`() {
        Assertions.assertNotNull(mockMvc, "mockMvc debe estar inicializado por Spring")
    }


}