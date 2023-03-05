package com.api.stockman.model

import jakarta.persistence.*

@Entity
@Table(name="session")
data class Session(
    @Id
    @Column(name="sessionID")
    var sessionID: String,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name="email_user", referencedColumnName = "email")
    var usuario: User
) {
}