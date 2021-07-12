package com.colutti.starwars.personages.dto.personage.response

data class PersonageResponse(
        var id: Long,
        var name: String,
        var birth: String,
        var death: String,
        var species: String,
        var description: String,
        var url_image: String,
        var actors: List<ActorResponse>,
        var planet: PlanetResponse?
)