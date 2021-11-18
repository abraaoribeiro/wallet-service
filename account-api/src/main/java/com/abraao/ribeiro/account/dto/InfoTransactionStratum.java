package com.abraao.ribeiro.account.dto;

import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InfoTransactionStratum {

    //TODO refatorar passar para classses o target e source

    private String referenceTransactionId;

    private BigDecimal transferredValue;

    private BigDecimal currentBalance;

    private BigDecimal previousBalance;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTimeTransaction;

    private String bankNumberSource;

    private String bankNumberTarget;

    private String bankNameSource;

    private String bankNameTarget;

    private String clientCpfSource;

    private String clientCpfTarget;

    private String clientNameSource;

    private String clientNameTarget;

    private TransactionType transactionType;
}
