package com.abraao.ribeiro.account.repository;

import com.abraao.ribeiro.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCpf(String cpf);

    @Modifying
    @Query("update Account u set u.balance = :balance where u.id = :id")
    void updateBalance(@Param(value = "balance") BigDecimal balance, @Param(value = "id") Long id);
}
