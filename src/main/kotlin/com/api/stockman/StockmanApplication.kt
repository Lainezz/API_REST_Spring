package com.api.stockman

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockmanApplication

fun main(args: Array<String>) {
	runApplication<StockmanApplication>(*args)
}
