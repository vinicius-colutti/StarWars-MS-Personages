package com.colutti.starwars.personages.repository

import com.colutti.starwars.personages.model.Personage
import org.springframework.data.repository.PagingAndSortingRepository

interface PersonageRepository: PagingAndSortingRepository<Personage, Long> {
}