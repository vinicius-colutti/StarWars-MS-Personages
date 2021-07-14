package com.colutti.starwars.personages.request

import com.colutti.starwars.personages.dto.client.MovieClientResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class MovieRequest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.movie.url}")
    val movieUrl: String? = null

    fun getMovie(movie_id: Long): MovieClientResponse {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return restTemplate.getForObject(movieUrl+"/"+movie_id, MovieClientResponse::class.java)!!
    }

}