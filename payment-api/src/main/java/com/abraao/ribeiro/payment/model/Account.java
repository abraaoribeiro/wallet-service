package com.abraao.ribeiro.payment.model;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
public class Account {

    @Embedded
    private Client client;

    @Embedded
    private Bank bank;
}
