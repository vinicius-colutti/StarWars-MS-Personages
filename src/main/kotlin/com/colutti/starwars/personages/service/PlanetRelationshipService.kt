package com.colutti.starwars.personages.service

import com.colutti.starwars.personages.model.PlanetRelationship

interface PlanetRelationshipService {

    fun save(planetRelationship: PlanetRelationship)
    fun deletePlanet(id: Long)
    fun teste(id: Long): PlanetRelationship


}