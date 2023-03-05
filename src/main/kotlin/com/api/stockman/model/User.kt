package com.api.stockman.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="user")
class User(
    @Column(name="nombre")
    var nombre: String,

    @Column(name="password")
    var password: String,

    @Column(name="entry_date")
    var entryDate: LocalDate,

    @Column(name="banned")
    var banned: Boolean,

    @OneToOne(mappedBy = "usuario", cascade = [CascadeType.ALL])
    var session: Session?,

    @Id
    @Column(name="email")
    var email: String,

) {

}