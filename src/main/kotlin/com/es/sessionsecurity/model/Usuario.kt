package com.es.sessionsecurity.model

import jakarta.persistence.*

@Suppress("JpaObjectClassSignatureInspection")
@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(unique = true)
    var nombre:String,
    var password:String,
    @Enumerated(EnumType.STRING)
    var rol: Rol
) {
}

enum class Rol {
    USER, ADMIN
}