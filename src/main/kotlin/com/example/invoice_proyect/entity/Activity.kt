package com.example.invoice_proyect.entity

import jakarta.persistence.*
import java.time.LocalDate
@Entity
@Table(name = "activity")
class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    var type: ActivityType? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "scheduled_at")
    var scheduledAt: LocalDate? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: ActivityStatus = ActivityStatus.PENDING

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDate = LocalDate.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDate = LocalDate.now()

    @ManyToOne
    @JoinColumn(name = "lead_id")
    var lead: Lead? = null

    // Actualiza la fecha de actualización antes de guardar
    @PreUpdate
    fun updateTimestamp() {
        updatedAt = LocalDate.now()
    }

    enum class ActivityType {
        CALL,
        EMAIL,
        MEETING,
        FOLLOW_UP
    }

    enum class ActivityStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }
}
