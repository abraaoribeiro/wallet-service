package com.abraao.ribeiro.account.repository;

import com.abraao.ribeiro.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByReferenceClientId(String referenceClientId);
}
