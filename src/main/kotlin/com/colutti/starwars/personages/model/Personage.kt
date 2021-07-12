package com.colutti.starwars.personages.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name="personage")
data class Personage(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var name: String = "",
        var birth: String = "",
        var death: String = "",
        var species: String = "",
        var description: String = "",
        var url_image: String = "",

        @JsonProperty("actors")
        @OneToMany(mappedBy = "personage", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        var actors: List<ActorRelationship> = emptyList(),

        @JsonProperty("planet")
        @OneToOne(mappedBy = "personage", cascade = [(CascadeType.ALL)], fetch= FetchType.EAGER)
        var planet: PlanetRelationship = PlanetRelationship()
)