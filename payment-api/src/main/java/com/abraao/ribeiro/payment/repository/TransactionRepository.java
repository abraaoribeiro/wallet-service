package com.abraao.ribeiro.payment.repository;

import com.abraao.ribeiro.payment.model.TransactionStratum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionStratum,Long> {
}
