package com.colutti.starwars.personages.dto.personage.converts

import com.colutti.starwars.personages.dto.personage.request.PersonageRequestDto
import com.colutti.starwars.personages.dto.personage.response.ActorResponse
import com.colutti.starwars.personages.dto.personage.response.PersonageResponse
import com.colutti.starwars.personages.dto.personage.response.PlanetResponse
import com.colutti.starwars.personages.model.ActorRelationship
import com.colutti.starwars.personages.model.Personage
import com.colutti.starwars.personages.model.PlanetRelationship
import org.springframework.stereotype.Component

@Component
class PersonageConverter {

    fun requestToPersonage(personageRequestDto: PersonageRequestDto): Personage{
        var actorList: List<ActorRelationship> = personageRequestDto.actors.map { ActorRelationship(0, it.actor_id, null) };
        var planet = PlanetRelationship(0, personageRequestDto.planet.planet_id, null)
        var personage = Personage(
                0, personageRequestDto.name, personageRequestDto.birth,
                personageRequestDto.death, personageRequestDto.species,
                personageRequestDto.description, personageRequestDto.url_image,
                actorList, planet)
        return personage
    }

    fun personageToResponse(personage: Personage, planetResponse: PlanetResponse, actorResponse: List<ActorResponse>): PersonageResponse{
        return PersonageResponse(
                personage.id, personage.name, personage.birth,
                personage.death, personage.species, personage.description,
                personage.url_image, actorResponse, planetResponse)
    }

}