package com.abraao.ribeiro.account.repository;

import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.Address;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public BankRepository bankRepository;

    @Test
    void given_Account_when_Create_then_Save_Account_Return_NotNull(){
        Address address = new Address();
        address.setNumber("52");
        address.setZipCode("658100");
        address.setCity("Belém");
        address.setComplement("Px ao super");
        address.setDistrict("Guanabara");
        address.setPlace("Passagem maria");
        address.setState("Pará");

        Client client = new Client();
        client.setName("Maria");
        client.setCpf("041-520-648-96");
        client.setAddress(address);

        Bank bank = new Bank();
        bank.setName("Itáu");
        bank.setNumber("522");
        bankRepository.save(bank);

        Account account = new Account();
        account.setBalance(new BigDecimal("500"));
        account.setBank(bank);
        account.setAgency("555");
        account.setNumber("001");
        account.setReferenceTransactionId(UUID.randomUUID().toString());
        account.setClient(client);

        assertNotNull(accountRepository.save(account));

    }

}
