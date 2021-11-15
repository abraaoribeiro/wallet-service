package com.abraao.ribeiro.client.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Builder
@Getter
public class InfoAccountDTO {

    private String number;

    private String agency;

    private BigDecimal balace;

    private Long bankId;

    private String referenceClientId;

}
