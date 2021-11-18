package com.abraao.ribeiro.payment.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDTO implements Serializable {

    private String number;

    private String referenceTransactionId;

    private String agency;

    private BigDecimal balance;

    private BankDTO bank;

    private ClientDTO client;

}
