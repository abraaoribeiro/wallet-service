package com.abraao.ribeiro.account.dto.account;

import lombok.Data;

@Data
public class ClientDTO {

    private String cpf;

    private String name;

    private AddressDTO address;
}
