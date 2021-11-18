package com.abraao.ribeiro.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numberAccount;

    @Column(nullable = false)
    private String agency;

    @Column(nullable = false)
    private BigDecimal balance;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Bank bank;

    @Column(nullable = false)
    private String referenceTransactionId;

    @Column(name = "client_cpf", nullable = false)
    private String cpf;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name = "address_zipCode", nullable = false)
    private String zipCode;

    @Column(name = "address_place", nullable = false)
    private String place;

    @Column(name = "address_number", nullable = false)
    private String number;

    @Column(name = "address_complement", nullable = false)
    private String complement;

    @Column(name = "address_district", nullable = false)
    private String district;

    @Column(name = "address_city", nullable = false)
    private String city;

    @Column(name = "address_state", nullable = false)
    private String state;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
