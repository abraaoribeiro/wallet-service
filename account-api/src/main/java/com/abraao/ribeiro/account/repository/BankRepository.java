package com.abraao.ribeiro.account.repository;

import com.abraao.ribeiro.account.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByNumber(String number);
}
