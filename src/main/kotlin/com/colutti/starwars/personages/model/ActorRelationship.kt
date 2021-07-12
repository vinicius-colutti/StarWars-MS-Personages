package com.colutti.starwars.personages.model

import javax.persistence.*

@Entity
@Table(name="actor_relationship")
data class ActorRelationship (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        var actor_id: Long = 0,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "personage_id")
        var personage: Personage? = null,
)