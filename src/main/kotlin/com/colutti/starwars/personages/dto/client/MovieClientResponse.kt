package com.colutti.starwars.personages.dto.client

import java.util.*

data class MovieClientResponse (
        var id: Long,
        var name: String,
        var release_date: Date,
        var image_url: String,
        var personages: List<PersonageClientResponse>
)