package com.example.invoice_proyect.entity

import jakarta.persistence.*

@Entity
@Table(name = "client")
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    var id: Long? = null,

    @Column(name = "full_name", nullable = false)
    var fullName: String? = null,

    @Column(nullable = false)
    var age: Int? = null,

    @Column(nullable = false)
    var address: String? = null,

    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    var invoice: MutableList<Invoice> = mutableListOf()
)
