package com.abraao.ribeiro.client.controller;

import com.abraao.ribeiro.client.dto.ClientDTO;
import com.abraao.ribeiro.client.model.Client;
import com.abraao.ribeiro.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/account/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createAccountClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.createAccountClient(clientDTO);
    }

    @GetMapping("/{cpf}")
    public Client findByCpf(@PathVariable String cpf){
        return clientService.getByCpf(cpf);
    }
}
