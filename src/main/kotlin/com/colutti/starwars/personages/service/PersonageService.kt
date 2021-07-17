package com.colutti.starwars.personages.service

import com.colutti.starwars.personages.dto.ResponseDefault
import com.colutti.starwars.personages.dto.personage.request.PersonageRequestDto
import com.colutti.starwars.personages.dto.personage.response.PersonageResponse

interface PersonageService {

    fun create(personageRequestDto: PersonageRequestDto)

    fun update(id: Long, personageRequestDto: PersonageRequestDto)

    fun getById(id: Long): PersonageResponse

    fun getAll(): List<PersonageResponse>

    fun getByMovieId(movie_id: Long): List<PersonageResponse>

}