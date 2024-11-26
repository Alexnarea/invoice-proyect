package com.example.invoice_proyect.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "lead")
class Lead {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "name", nullable = false)
    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var createdAt: LocalDate? = LocalDate.now()
    var updatedAt: LocalDate? = LocalDate.now()
}
