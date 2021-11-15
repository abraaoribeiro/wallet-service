package com.abraao.ribeiro.account.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class AccountDTO {

    private String number;

    private String agency;

    private BigDecimal balace;

    private Long bankId;

    private String referenceClientId;

}
