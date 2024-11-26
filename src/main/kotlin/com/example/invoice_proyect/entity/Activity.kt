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
    @Column(name = "description")
    var description: String? = null
    var scheduledAt: LocalDate? = LocalDate.now()
    var createdAt: LocalDate? = LocalDate.now()
    var updatedAt: LocalDate? = LocalDate.now()

    @ManyToOne
    @JoinColumn(name = "lead_id")
    var lead: Lead? = null

}
