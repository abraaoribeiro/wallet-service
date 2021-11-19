package com.abraao.ribeiro.payment.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Bank {

    @Column(name = "bank_number")
    private String number;

    @Column(name = "bank_name")
    private String name;
}