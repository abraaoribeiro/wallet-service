package com.abraao.ribeiro.account.dto;

import com.abraao.ribeiro.account.model.enums.TransactionType;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class InfoTransactionDTO  implements Serializable {

    private BigDecimal value;

    private TransactionType transactionType;

    private AccountDTO accountSource;

    private AccountDTO accountTarget;
}
