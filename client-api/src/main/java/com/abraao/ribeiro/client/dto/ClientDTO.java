package com.abraao.ribeiro.client.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;

@Getter
public class ClientDTO {

    @CPF(message = "O formato do cpf deve ser valido")
    @NotBlank(message = "O campo cpf não pode ser nulo e nem vazio")
    private String cpf;

    private String name;

    private AddresDTO address;

    private InfoAccountDTO account;
}
