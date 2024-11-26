package com.example.invoice_proyect.repository
import com.example.invoice_proyect.entity.Lead
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface LeadRepository : JpaRepository<Lead, Long> {
}