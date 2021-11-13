package com.abraao.ribeiro.client.service;

import com.abraao.ribeiro.client.dto.ClientDTO;
import com.abraao.ribeiro.client.exception.ClientNotFoundException;
import com.abraao.ribeiro.client.model.Client;
import com.abraao.ribeiro.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(ClientDTO clientDTO) {

        Client clientSave = Client.builder()
                .cpf(clientDTO.getCpf())
                .zipCode(clientDTO.getAddress().getZipCode())
                .number(clientDTO.getAddress().getNumber())
                .complement(clientDTO.getAddress().getComplement())
                .district(clientDTO.getAddress().getDistrict())
                .place(clientDTO.getAddress().getPlace())
                .reverenceId("UUID")
                .build();

        return clientRepository.save(clientSave);
    }

    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(id);
        }
    }

    public Client findOrFail(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

}
