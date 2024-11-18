package com.example.invoice_proyect.mapper

import com.example.invoice_proyect.dto.ClientDto
import com.example.invoice_proyect.entity.Client
import org.springframework.stereotype.Component

@Component
object ClientMapper {
    fun toEntity(clientDto: ClientDto): Client {
        val client = Client()
        client.fullName = clientDto.fullName
        client.age = clientDto.age
        client.address = clientDto.address
        return client
    }

    fun toDto(client: Client): ClientDto {
        val clientDto = ClientDto()
        clientDto.id = client.id
        clientDto.fullName = client.fullName
        clientDto.age = client.age
        clientDto.address = client.address
        return clientDto
    }
}