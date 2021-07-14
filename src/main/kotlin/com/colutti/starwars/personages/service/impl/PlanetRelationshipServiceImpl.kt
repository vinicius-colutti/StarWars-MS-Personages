package com.colutti.starwars.personages.service.impl

import com.colutti.starwars.personages.model.PlanetRelationship
import com.colutti.starwars.personages.repository.PlanetRelationshipRepository
import com.colutti.starwars.personages.service.PlanetRelationshipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class PlanetRelationshipServiceImpl: PlanetRelationshipService {

    @Autowired
    lateinit var planetRelationshipRepository: PlanetRelationshipRepository

    @CacheEvict("personages", allEntries = true)
    override fun save(planetRelationship: PlanetRelationship) {
        planetRelationshipRepository.save(planetRelationship)
    }

    @CacheEvict("personages", allEntries = true)
    override fun deletePlanet(id: Long) {
        planetRelationshipRepository.deletePlanet(id)
    }

    override fun teste(id: Long) =
        planetRelationshipRepository.findByPersonageId(id)
}