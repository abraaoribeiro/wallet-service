package com.abraao.ribeiro.client.repository;

import com.abraao.ribeiro.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByCpf(String cpf);
}
