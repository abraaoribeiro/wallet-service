package com.abraao.ribeiro.payment.dto;

import com.abraao.ribeiro.payment.model.enums.TransactionType;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransactionDTO implements Serializable {

    private BigDecimal value;

    private TransactionType transactionType;

    private AccountDTO accountSource;

    private AccountDTO accountTarget;

}
