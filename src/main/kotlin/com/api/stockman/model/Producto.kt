package com.api.stockman.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "producto")
class Producto(
    @Column(name="name")
    var name: String,
    @Column(name="quantity")
    var quantity: Int,
    @Column(name="price")
    var price: Double,
    @Column(name="entryDate")
    var entryDate: LocalDate,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id: Long?
) {
}