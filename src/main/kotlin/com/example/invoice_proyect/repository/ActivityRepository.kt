package com.example.invoice_proyect.repository

import com.example.invoice_proyect.entity.Activity
import com.example.invoice_proyect.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository: JpaRepository<Activity, Long> {
    fun findByLeadId(leadId: Long): List<Activity>

}