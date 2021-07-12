package com.colutti.starwars.personages

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonagesApplication

fun main(args: Array<String>) {
	runApplication<PersonagesApplication>(*args)
}
