package com.api.stockman

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Diego
 * @since 1.0
 */
@SpringBootApplication
class StockmanApplication

fun main(args: Array<String>) {
	runApplication<StockmanApplication>(*args)
}
