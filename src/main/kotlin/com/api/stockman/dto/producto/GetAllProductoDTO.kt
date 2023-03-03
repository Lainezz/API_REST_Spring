package com.api.stockman.dto.producto


class GetAllProductoDTO(
    var id: Long,
    var name: String,
    var quantity: Int,
    var price: Double) {
}