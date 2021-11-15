package com.abraao.ribeiro.client.service;

import com.abraao.ribeiro.client.dto.ClientDTO;
import com.abraao.ribeiro.client.dto.InfoAccountDTO;
import com.abraao.ribeiro.client.exception.ClientNotFoundException;
import com.abraao.ribeiro.client.external.client.AccountResource;
import com.abraao.ribeiro.client.model.Client;
import com.abraao.ribeiro.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final AccountResource accountClient;

    public Client createAccountClient(ClientDTO clientDTO) {
        Client clientSave = Client.builder()
                .name(clientDTO.getName())
                .cpf(clientDTO.getCpf())
                .zipCode(clientDTO.getAddress().getZipCode())
                .number(clientDTO.getAddress().getNumber())
                .complement(clientDTO.getAddress().getComplement())
                .district(clientDTO.getAddress().getDistrict())
                .place(clientDTO.getAddress().getPlace())
                .city(clientDTO.getAddress().getCity())
                .state(clientDTO.getAddress().getState())
                .referenceId(UUID.randomUUID().toString())
                .build();

        InfoAccountDTO infoAccountDTO = InfoAccountDTO.builder()
                .agency(clientDTO.getAccount().getAgency())
                .number(clientDTO.getAccount().getNumber())
                .bankId(clientDTO.getAccount().getBankId())
                .balace(clientDTO.getAccount().getBalace())
                .referenceClientId(clientSave.getReferenceId())
                .build();

        accountClient.createAccount(infoAccountDTO);

        return clientRepository.save(clientSave);
    }


    public Client getByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(() -> new ClientNotFoundException(cpf));
    }
}
