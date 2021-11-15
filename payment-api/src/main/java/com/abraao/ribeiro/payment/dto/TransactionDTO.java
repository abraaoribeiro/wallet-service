package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDTO {

    private BigDecimal value;

    private InfoAccountDTO accountSource;

    private InfoAccountDTO accountTarget;

}
