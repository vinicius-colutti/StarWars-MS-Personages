package com.colutti.starwars.personages.service

import com.colutti.starwars.personages.model.ActorRelationship

interface ActorRelationshipService {

    fun save(actorRelationship: ActorRelationship)
    fun deleteActors(id: Long)
    fun findByPersonageId(id: Long): List<ActorRelationship>
}