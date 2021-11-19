package com.abraao.ribeiro.account.dto.transaction;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionStratumListDTO {

    private String referenceTransactionId;

    private BigDecimal transferredValue;

    private BigDecimal currentBalance;

    private BigDecimal previousBalance;

    private LocalDateTime dateTimeTransaction;

}
