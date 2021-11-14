package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class AccountSource {

    private String namber;

    private String agency;

    private BigDecimal balace;

    private BankDTO bank;
}
