package com.colutti.starwars.personages.request

import com.colutti.starwars.personages.dto.client.MovieClientResponse
import com.colutti.starwars.personages.exception.MovieNotFoundException
import com.colutti.starwars.personages.exception.RequestClientException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.net.ConnectException

@Component
class MovieRequest {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.movie.url}")
    val movieUrl: String? = null

    fun getMovie(movie_id: Long): MovieClientResponse {
        try {
            val httpHeaders = HttpHeaders()
            httpHeaders.contentType = MediaType.APPLICATION_JSON
            return restTemplate.getForObject(movieUrl+"/"+movie_id, MovieClientResponse::class.java)!!
        }catch (clientErr: HttpClientErrorException){
            if(clientErr.statusCode == HttpStatus.NOT_FOUND){
                throw MovieNotFoundException("Movie "+ movie_id +" not found")
            }else{
                throw RequestClientException("Movie API unavailable, status Code: "+ clientErr.statusCode)
            }
        }catch (connectError: ConnectException){
            throw RequestClientException("Movie API unavailable, message: "+ connectError.message)
        }
    }

}