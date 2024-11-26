package com.example.invoice_proyect.service

import com.example.invoice_proyect.dto.ClientDto
import com.example.invoice_proyect.entity.Invoice
import com.example.invoice_proyect.mapper.ClientMapper
import com.example.invoice_proyect.repository.ClientRepository
import com.example.invoice_proyect.repository.InvoiceRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class ClientService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var clientMapper: ClientMapper


    //Obtener todos los clientes
    fun findAll(): List<ClientDto> {
        val clients = clientRepository.findAll()
        return clients.map {clientMapper.toDto(it) }
    }

    //Obtener un cliente por su ID
    fun findById(id: Long): ClientDto {
        val client = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Client with ID $id not found") }
        return clientMapper.toDto(client)
    }
    fun findInvoicesByClientId(id: Long): List<Invoice> {
        return invoiceRepository.findByClientId(id) ?: emptyList()
    }

    fun save(clientDto: ClientDto): ClientDto {
        val client = clientMapper.toEntity(clientDto)
        val savedClient = clientRepository.save(client)
        return clientMapper.toDto(savedClient)
    }

    fun updateClient(id: Long, clientDto: ClientDto): ClientDto {
        val client = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Client with ID $id not found") }
        client.apply {
            fullName = clientDto.fullName
            age = clientDto.age
            address = clientDto.address
        }

        val updatedClient = clientRepository.save(client)
        return clientMapper.toDto(updatedClient)
    }


    fun deleteClient(id: Long) {
        val client = clientRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Could not find client with id: $id") }
        clientRepository.delete(client)
    }
}