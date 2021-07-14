package com.colutti.starwars.personages.dto.personage.converts

import com.colutti.starwars.personages.dto.personage.response.PlanetResponse
import com.colutti.starwars.personages.model.PlanetRelationship
import org.springframework.stereotype.Component

@Component
class PlanetConverter {

    fun planetToReponse(planetRelationship: PlanetRelationship): PlanetResponse{
        return PlanetResponse(planetRelationship.planet_id)
    }

}