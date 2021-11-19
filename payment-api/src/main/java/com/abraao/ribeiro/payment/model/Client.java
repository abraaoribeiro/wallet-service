package com.abraao.ribeiro.payment.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Client {

    @Column(nullable = false, name = "client_cpf")
    private String cpf;

    @Column(nullable = false, name = "client_name")
    private String name;
}
