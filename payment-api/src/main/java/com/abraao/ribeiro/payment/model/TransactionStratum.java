package com.abraao.ribeiro.payment.model;

import com.abraao.ribeiro.payment.model.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
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
import java.util.UUID;

@Entity
@Getter
@Setter
public class TransactionStratum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String referenceTransactionId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private BigDecimal currentValue;

    @CreationTimestamp
    private LocalDateTime dateTimeTransaction;

    @Column(nullable = false)
    private String targetClient;

    @Column(nullable = false)
    private String sourceClient;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
