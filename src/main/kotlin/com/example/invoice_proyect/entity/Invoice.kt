package com.example.invoice_proyect.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "invoice")
class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null
    @Column(name = "name")
    var code: String? = null
    var createdAt: LocalDate? = null
    var total: Double? = null
    @ManyToOne
    @JoinColumn(name = "client_id")
    var client: Client? = null
}