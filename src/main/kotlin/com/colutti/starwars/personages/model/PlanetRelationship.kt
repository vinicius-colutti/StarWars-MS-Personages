package com.colutti.starwars.personages.model

import javax.persistence.*

@Entity
@Table(name="planet_relationship")
data class PlanetRelationship (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        var planet_id: Long = 0,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "personage_id")
        var personage: Personage? = null,
)