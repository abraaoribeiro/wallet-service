package com.abraao.ribeiro.payment.model;

import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStratum {

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

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "client.name", column = @Column(name = "client_name_source")),
            @AttributeOverride(name = "client.cpf", column = @Column(name = "client_cpf_source")),
            @AttributeOverride(name = "bank.number", column = @Column(name = "bank_number_source")),
            @AttributeOverride(name = "bank.name", column = @Column(name = "bank_name_source"))
    })
    private Account accountSource;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "client.name", column = @Column(name = "client_name_target")),
            @AttributeOverride(name = "client.cpf", column = @Column(name = "client_cpf_target")),
            @AttributeOverride(name = "bank.number", column = @Column(name = "bank_number_target")),
            @AttributeOverride(name = "bank.name", column = @Column(name = "bank_name_target"))
    })
    private Account accountTarget;

}
