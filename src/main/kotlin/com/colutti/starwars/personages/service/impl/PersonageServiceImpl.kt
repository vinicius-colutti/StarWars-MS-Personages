package com.colutti.starwars.personages.service.impl

import com.colutti.starwars.personages.dto.client.PersonageClientResponse
import com.colutti.starwars.personages.dto.personage.converts.ActorConverter
import com.colutti.starwars.personages.dto.personage.converts.PersonageConverter
import com.colutti.starwars.personages.dto.personage.converts.PlanetConverter
import com.colutti.starwars.personages.dto.personage.request.PersonageRequestDto
import com.colutti.starwars.personages.dto.personage.response.PersonageResponse
import com.colutti.starwars.personages.exception.PersonageNotFoundException
import com.colutti.starwars.personages.model.ActorRelationship
import com.colutti.starwars.personages.model.Personage
import com.colutti.starwars.personages.model.PlanetRelationship
import com.colutti.starwars.personages.repository.PersonageRepository
import com.colutti.starwars.personages.request.MovieRequest
import com.colutti.starwars.personages.service.ActorRelationshipService
import com.colutti.starwars.personages.service.PersonageService
import com.colutti.starwars.personages.service.PlanetRelationshipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class PersonageServiceImpl: PersonageService {

    @Autowired
    lateinit var converter: PersonageConverter
    @Autowired
    lateinit var personageRepository: PersonageRepository
    @Autowired
    lateinit var actorRelationshipService: ActorRelationshipService
    @Autowired
    lateinit var planetRelationshipService: PlanetRelationshipService
    @Autowired
    lateinit var planetConverter: PlanetConverter
    @Autowired
    lateinit var actorConverter: ActorConverter
    @Autowired
    lateinit var movieRequest: MovieRequest

    @CacheEvict("personages", allEntries = true)
    override fun create(personageRequestDto: PersonageRequestDto) {
        var personageToSave = this.converter.requestToPersonage(personageRequestDto)
        var savedPersonage = personageRepository.save(personageToSave)
        personageToSave.actors.map { actor ->
            actor.personage = savedPersonage
            actorRelationshipService.save(actor)
        }
        planetRelationshipService.save(PlanetRelationship(0, personageRequestDto.planet.planet_id, savedPersonage))
    }

    @CacheEvict("personages", allEntries = true)
    override fun update(id: Long, personageRequestDto: PersonageRequestDto) {
        personageRepository.findById(id)
                .orElseGet { throw PersonageNotFoundException("Personage ${id} not found")}
        actorRelationshipService.deleteActors(id)
        planetRelationshipService.deletePlanet(id)
        var personageToUpdate = this.converter.requestToPersonage(personageRequestDto)
        personageToUpdate.id = id
        personageToUpdate.actors = personageToUpdate.actors.map { actor ->
            ActorRelationship(0, actor.actor_id , personageToUpdate)
        }
        personageToUpdate.planet = PlanetRelationship(0, personageToUpdate.planet.planet_id, personageToUpdate)
        personageRepository.save(personageToUpdate)
    }

    override fun getById(id: Long): PersonageResponse {
        val personage: Personage = personageRepository.findById(id)
                .orElseGet { throw PersonageNotFoundException("Personage ${id} not found") }
        val actorList: List<ActorRelationship> = actorRelationshipService.findByPersonageId(id).toList()
        val idPlanet: PlanetRelationship = planetRelationshipService.teste(id)
        return converter.personageToResponse(personage,planetConverter.planetToReponse(idPlanet),
                actorConverter.actorToResponse(actorList))
    }

    @Cacheable("personages")
    override fun getAll(): List<PersonageResponse> {
        val personageList: List<Personage> = personageRepository.findAll().toList()
        return personageList.map { getById(it.id) }
    }

    override fun getByMovieId(movie_id: Long): List<PersonageResponse> {
        var movieResponse = movieRequest.getMovie(movie_id);
        return movieResponse.personages.map { p -> getById(p.personage_id) }
    }

}