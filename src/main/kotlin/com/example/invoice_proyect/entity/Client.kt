package com.example.invoice_proyect.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "client")
class Client(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null,
    @Column(name = "full_name")
    var fullName: String? = null,
    var age: Int? = null,
    var address: String? = null,
    @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
    var invoice: MutableList<Invoice> = mutableListOf()
)
