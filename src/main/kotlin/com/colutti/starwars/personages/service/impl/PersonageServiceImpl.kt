package com.colutti.starwars.personages.service.impl

import com.colutti.starwars.personages.dto.personage.converts.PersonageConverter
import com.colutti.starwars.personages.dto.personage.request.PersonageRequestDto
import com.colutti.starwars.personages.dto.personage.response.ActorResponse
import com.colutti.starwars.personages.dto.personage.response.PersonageResponse
import com.colutti.starwars.personages.dto.personage.response.PlanetResponse
import com.colutti.starwars.personages.exception.PersonageNotFoundException
import com.colutti.starwars.personages.model.ActorRelationship
import com.colutti.starwars.personages.model.Personage
import com.colutti.starwars.personages.model.PlanetRelationship
import com.colutti.starwars.personages.repository.ActorRelationshipRepository
import com.colutti.starwars.personages.repository.PersonageRepository
import com.colutti.starwars.personages.repository.PlanetRelationshipRepository
import com.colutti.starwars.personages.request.ActorRequest
import com.colutti.starwars.personages.request.PlanetRequest
import com.colutti.starwars.personages.service.PersonageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonageServiceImpl: PersonageService {

    @Autowired
    lateinit var converter: PersonageConverter

    @Autowired
    lateinit var personageRepository: PersonageRepository

    @Autowired
    lateinit var actorRelationshipRepository: ActorRelationshipRepository

    @Autowired
    lateinit var planetRelationshipRepository: PlanetRelationshipRepository

    @Autowired
    lateinit var actorRequest: ActorRequest

    @Autowired
    lateinit var planetRequest: PlanetRequest

    override fun create(personageRequestDto: PersonageRequestDto) {
        var personageToSave = this.converter.requestToPersonage(personageRequestDto)
        var savedPersonage = personageRepository.save(personageToSave)
        personageToSave.actors.map { actor ->
            actor.personage = savedPersonage
            actorRelationshipRepository.save(actor)
        }
        planetRelationshipRepository.save(PlanetRelationship(0, personageRequestDto.planet.planet_id, savedPersonage))
    }

    override fun update(id: Long, personageRequestDto: PersonageRequestDto) {
        personageRepository.findById(id).orElseGet { throw PersonageNotFoundException("Personage ${id} not found")}
        actorRelationshipRepository.deleteActors(id)
        planetRelationshipRepository.deletePlanet(id)
        var personageToUpdate = this.converter.requestToPersonage(personageRequestDto)
        personageToUpdate.id = id
        personageToUpdate.actors = personageToUpdate.actors.map { actor ->
            ActorRelationship(0, actor.actor_id , personageToUpdate)
        }
        personageToUpdate.planet = PlanetRelationship(0, personageToUpdate.planet.planet_id, personageToUpdate)
        personageRepository.save(personageToUpdate)
    }

    override fun getById(id: Long): PersonageResponse {
        val personage: Personage = personageRepository.findById(id).orElseGet { throw PersonageNotFoundException("Personage ${id} not found") }
        val actorList: List<ActorRelationship> = actorRelationshipRepository.findByPersonageId(id).toList()
        val actorResponseList: List<ActorResponse> = actorList.map{ actorRequest.getActor(it.actor_id) }
        val idPlanet: PlanetRelationship = planetRelationshipRepository.findByPersonageId(id)
        val planetResponse: PlanetResponse = planetRequest.getPlanet(idPlanet.planet_id)
        return converter.personageToResponse(personage,planetResponse,actorResponseList)
    }

    override fun getAll(): List<PersonageResponse> {
        val personageList: List<Personage> = personageRepository.findAll().toList()
        val responseList: List<PersonageResponse> = personageList.map { getById(it.id) }
        return responseList
    }

}