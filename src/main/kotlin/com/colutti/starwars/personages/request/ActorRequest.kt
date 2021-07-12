package com.colutti.starwars.personages.request

import com.colutti.starwars.personages.dto.personage.response.ActorResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ActorRequest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.actor.url}")
    val actorUrl: String? = null

    fun getActor(actor_id: Long): ActorResponse {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return restTemplate.getForObject(actorUrl+"/"+actor_id, ActorResponse::class.java)!!
    }

}