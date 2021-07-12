package com.colutti.starwars.personages.repository

import com.colutti.starwars.personages.model.ActorRelationship
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import javax.transaction.Transactional

interface ActorRelationshipRepository: PagingAndSortingRepository<ActorRelationship, Long> {

    @Query(value="SELECT * FROM actor_relationship a WHERE a.personage_id = ?1", nativeQuery = true)
    fun findByPersonageId(personage_id: Long): Iterable<ActorRelationship>

    @Modifying
    @Transactional
    @Query(value="DELETE FROM actor_relationship a WHERE a.personage_id = ?1", nativeQuery = true)
    fun deleteActors(personage_id: Long)

}