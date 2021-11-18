package com.abraao.ribeiro.payment.model;

import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStratum {

    //TODO separar em entidades e passar o DTO no reuquest;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String referenceTransactionId;

    @Column(nullable = false)
    private BigDecimal transferredValue;

    @Column(nullable = false)
    private BigDecimal currentBalance;

    @Column(nullable = false)
    private BigDecimal previousBalance;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @CreationTimestamp
    private LocalDateTime dateTimeTransaction;

    @Column(name = "clientCpfSource")
    private String clientCpfSource;

    @Column(name = "clientNameSource")
    private String clientNameSource;

    @Column(name = "bankNameSource")
    private String bankNameSource;

    @Column(name = "bankNumberSource")
    private String bankNumberSource;

    @Column(name = "bankNumberTarget")
    private String bankNumberTarget;

    @Column(name = "bankNameTarget")
    private String bankNameTarget;

    @Column(name = "clientCpfTarget")
    private String clientCpfTarget;

    @Column(name = "clientNameTarget")
    private String clientNameTarget;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
