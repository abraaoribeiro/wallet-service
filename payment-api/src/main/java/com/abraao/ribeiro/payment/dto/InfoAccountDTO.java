package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class InfoAccountDTO {

    private Long id;

    private String number;

    private BigDecimal balance;

    private String agency;

    private Long bankId;

    private InfoClientDTO client;

}
