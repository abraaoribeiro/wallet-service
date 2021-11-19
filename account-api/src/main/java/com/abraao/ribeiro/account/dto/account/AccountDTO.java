package com.abraao.ribeiro.account.dto.account;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDTO implements Serializable {

    private String referenceTransactionId;

    private String number;

    private String agency;

    private BigDecimal balance;

    private BankDTO bank;

    private ClientDTO client;

}
