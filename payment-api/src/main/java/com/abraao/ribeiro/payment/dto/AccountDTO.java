package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {

    private BigDecimal value;

    private BankDTO bank;

    private AccountSource accountSource;

    private AccountTarget accountTarget;


}
