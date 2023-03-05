package com.api.stockman.model

import jakarta.persistence.*

@Entity
@Table(name="session")
class Session(
    @Id
    @Column(name="sessionID")
    var sessionID: String,

    @Id
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    var usuario: User
) {
}