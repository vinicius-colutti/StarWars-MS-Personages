package com.colutti.starwars.personages.dto.personage.converts

import com.colutti.starwars.personages.dto.personage.response.ActorResponse
import com.colutti.starwars.personages.model.ActorRelationship
import org.springframework.stereotype.Component

@Component
class ActorConverter {

    fun actorToResponse(actorRelationship: List<ActorRelationship>): List<ActorResponse> {
        return actorRelationship.map { ActorResponse(it.actor_id) }
    }

}