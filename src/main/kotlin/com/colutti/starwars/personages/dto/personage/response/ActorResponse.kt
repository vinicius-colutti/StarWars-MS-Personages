package com.colutti.starwars.personages.dto.personage.response

import java.util.*

data class ActorResponse (
        var id: Long,
        var name: String,
        var url_image: String,
        var birth_date: Date
)