package com.example.invoice_proyect.entity

import jakarta.persistence.*
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

    @Column(name = "email", nullable = false, unique = true)
    var email: String? = null

    @Column(name = "phone")
    var phone: String? = null

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: LeadStatus = LeadStatus.NEW  // Cambiar a var si planeas modificar el estado

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @PreUpdate
    fun updateTimestamp() {
        updatedAt = LocalDateTime.now()  // Actualiza updatedAt antes de persistir
    }

    enum class LeadStatus {
        NEW,
        CONTACTED,
        QUALIFIED,
        CONVERTED,
        CLOSED
    }
}
