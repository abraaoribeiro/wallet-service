package com.abraao.ribeiro.account.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
public class Client {

    @Column(name = "client_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Embedded
    Address address;
}
