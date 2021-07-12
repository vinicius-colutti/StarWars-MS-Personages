package com.colutti.starwars.personages.controller

import com.colutti.starwars.personages.dto.ResponseDefault
import com.colutti.starwars.personages.dto.personage.request.PersonageRequestDto
import com.colutti.starwars.personages.service.PersonageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value=["/starwars/personages"])
class PersonageController {

    @Autowired
    lateinit var service: PersonageService

    @PostMapping
    fun create(@RequestBody personageRequestDto: PersonageRequestDto): ResponseEntity<ResponseDefault> {
        service.create(personageRequestDto)
        val responseJson = ResponseDefault("Created personage!", Date())
        return ResponseEntity(responseJson, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody personageRequestDto: PersonageRequestDto): ResponseEntity<ResponseDefault> {
        service.update(id, personageRequestDto)
        val responseJson = ResponseDefault("Updated personage!", Date())
        return ResponseEntity(responseJson, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long) =
            ResponseEntity(service.getById(id),HttpStatus.OK)

    @GetMapping
    fun getAll() =
            ResponseEntity(service.getAll(),HttpStatus.OK)


}