package com.abraao.ribeiro.account.dto;

import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionStratumDTO {

    private String referenceTransactionId;

    private BigDecimal transferredValue;

    private BigDecimal currentBalance;

    private BigDecimal previousBalance;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @CreationTimestamp
    private LocalDateTime dateTimeTransaction;

    private TransactionType transactionType;

    private AccountDTO accountSource;

    private AccountDTO accountTarget;
}
