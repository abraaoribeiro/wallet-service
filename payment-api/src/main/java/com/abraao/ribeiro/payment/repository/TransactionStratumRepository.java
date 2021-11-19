package com.abraao.ribeiro.payment.repository;

import com.abraao.ribeiro.payment.model.TransactionStratum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransactionStratumRepository extends JpaRepository<TransactionStratum,Long> {

    Optional<TransactionStratum> findByReferenceTransactionId(String referenceTransactionId);

    Page<TransactionStratum> findAll(Pageable pageable);


}
