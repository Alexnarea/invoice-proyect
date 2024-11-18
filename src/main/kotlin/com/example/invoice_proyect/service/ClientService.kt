package com.example.invoice_proyect.service

import com.example.invoice_proyect.dto.ClientDto
import com.example.invoice_proyect.mapper.ClientMapper
import com.example.invoice_proyect.repository.ClientRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var clientMapper: ClientMapper


    //Obtener todos los clientes
    fun getAllClient(): List<ClientDto> {
        val clients = clientRepository.findAll()
        return clients.map {clientMapper.toDto(it) }
    }

    //Obtener un cliente por su ID
    fun getClientById(id: Long): ClientDto {
        val client = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Could not find client with id: $id") }
        return clientMapper.toDto(client)
    }
    fun save(clientDto: ClientDto): ClientDto {
        val client = clientMapper.toEntity(clientDto)
        val savedClient = clientRepository.save(client)
        return clientMapper.toDto(savedClient)
    }

fun updateClient(id: Long, clientDto: ClientDto): ClientDto {
    val client = clientRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Could not find client with id: $id") }
    client.fullName = clientDto.fullName
    client.age = clientDto.age
    client.address = clientDto.address
    val updateClient = clientRepository.save(client)
    return clientMapper.toDto(updateClient)

}
    fun deleteClient(id: Long) {
        val client = clientRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Could not find client with id: $id")
            }
        clientRepository.delete(client)
    }
}