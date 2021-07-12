package com.colutti.starwars.personages.request

import com.colutti.starwars.personages.dto.personage.response.PlanetResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PlanetRequest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.planet.url}")
    val planetUrl: String? = null

    fun getPlanet(planet_id: Long): PlanetResponse {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return restTemplate.getForObject(planetUrl+"/"+planet_id, PlanetResponse::class.java)!!
    }

}