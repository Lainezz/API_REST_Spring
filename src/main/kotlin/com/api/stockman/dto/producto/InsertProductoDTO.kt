package com.api.stockman.dto.producto

import com.api.stockman.model.Producto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


class InsertProductoDTO(
    var name: String,
    var quantity: Int,
    var price: Double) {
}