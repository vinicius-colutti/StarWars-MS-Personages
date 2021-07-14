package com.colutti.starwars.personages.service.impl

import com.colutti.starwars.personages.model.ActorRelationship
import com.colutti.starwars.personages.repository.ActorRelationshipRepository
import com.colutti.starwars.personages.service.ActorRelationshipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ActorRelationshipServiceImpl: ActorRelationshipService {

    @Autowired
    lateinit var actorRelationshipRepository: ActorRelationshipRepository

    @CacheEvict("personages", allEntries = true)
    override fun save(actorRelationship: ActorRelationship) {
        actorRelationshipRepository.save(actorRelationship)
    }

    @CacheEvict("personages", allEntries = true)
    override fun deleteActors(id: Long) {
        actorRelationshipRepository.deleteActors(id)
    }

    @Cacheable("personages")
    override fun findByPersonageId(id: Long): List<ActorRelationship> {
        return actorRelationshipRepository.findByPersonageId(id).toList()
    }
}