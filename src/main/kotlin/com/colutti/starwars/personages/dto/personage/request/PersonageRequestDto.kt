package com.colutti.starwars.personages.dto.personage.request


data class PersonageRequestDto (
    var name: String = "",
    var birth: String = "",
    var death: String = "",
    var species: String = "",
    var description: String = "",
    var url_image: String = "",
    var actors: List<ActorRequestDto>,
    var planet: PlanetRequestDto
)