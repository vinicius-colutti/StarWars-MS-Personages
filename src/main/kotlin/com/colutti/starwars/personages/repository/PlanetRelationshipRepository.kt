package com.colutti.starwars.personages.repository

import com.colutti.starwars.personages.model.PlanetRelationship
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import javax.transaction.Transactional

interface PlanetRelationshipRepository: PagingAndSortingRepository<PlanetRelationship, Long> {

    @Query(value="SELECT * FROM planet_relationship p WHERE p.personage_id = ?1", nativeQuery = true)
    fun findByPersonageId(personage_id: Long): PlanetRelationship

    @Modifying
    @Transactional
    @Query(value="DELETE FROM planet_relationship p WHERE p.personage_id = ?1", nativeQuery = true)
    fun deletePlanet(personage_id: Long)

}