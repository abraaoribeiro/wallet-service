package com.abraao.ribeiro.payment.repository;

import com.abraao.ribeiro.payment.model.Account;
import com.abraao.ribeiro.payment.model.Bank;
import com.abraao.ribeiro.payment.model.Client;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    public TransactionStratumRepository transactionStratumRepository;

    @Test
    void given_Transaction_Stratum_when_Create_then_Save_Trasaction_Stratum_Return_NotNull() {
        Bank bankSouce = new Bank();
        bankSouce.setName("Banco do Brasil");
        bankSouce.setNumber("522");

        Client clientSource = new Client();
        clientSource.setCpf("640.489.460-21");
        clientSource.setName("João da Silva");

        Account accountSource = new Account();
        accountSource.setBank(bankSouce);
        accountSource.setClient(clientSource);

        TransactionStratum transactionStratumSource = new TransactionStratum();
        transactionStratumSource.setReferenceTransactionId(UUID.randomUUID().toString());
        transactionStratumSource.setTransactionType(TransactionType.TRANSFERENC);
        transactionStratumSource.setCurrentBalance(new BigDecimal("200"));
        transactionStratumSource.setPreviousBalance(new BigDecimal("100"));
        transactionStratumSource.setTransferredValue(new BigDecimal("50"));
        transactionStratumSource.setAccountSource(accountSource);

        Bank bankTarget = new Bank();
        bankSouce.setName("Banco do Brasil");
        bankSouce.setNumber("522");

        Client clientTarget = new Client();
        clientTarget.setCpf("554.489.460-21");
        clientTarget.setName("Maria da Silva");

        Account accountTarget = new Account();
        accountTarget.setBank(bankTarget);
        accountTarget.setClient(clientTarget);

        TransactionStratum transactionStratumTarget = new TransactionStratum();
        transactionStratumTarget.setReferenceTransactionId(UUID.randomUUID().toString());
        transactionStratumTarget.setTransactionType(TransactionType.TRANSFERENC);
        transactionStratumTarget.setCurrentBalance(new BigDecimal("200"));
        transactionStratumTarget.setPreviousBalance(new BigDecimal("100"));
        transactionStratumTarget.setTransferredValue(new BigDecimal("50"));
        transactionStratumTarget.setAccountSource(accountSource);

        TransactionStratum transactionStratum = transactionStratumRepository.save(transactionStratumSource);

        assertNotNull(transactionStratum);
    }

    @Test
    void given_Transaction_Stratum_when_Find_For_Reference_Transaction_Id_then_Equal_Transaction_Stratum_Save() {
        Bank bank = new Bank();
        bank.setName("Banco do Brasil");
        bank.setNumber("522");

        Client client = new Client();
        client.setCpf("554.489.460-21");
        client.setName("Maria da Silva");

        Account account = new Account();
        account.setBank(bank);
        account.setClient(client);

        TransactionStratum newTransactionStratum = new TransactionStratum();
        newTransactionStratum.setReferenceTransactionId(UUID.randomUUID().toString());
        newTransactionStratum.setTransactionType(TransactionType.TRANSFERENC);
        newTransactionStratum.setCurrentBalance(new BigDecimal("200"));
        newTransactionStratum.setPreviousBalance(new BigDecimal("100"));
        newTransactionStratum.setTransferredValue(new BigDecimal("50"));
        newTransactionStratum.setAccountSource(account);

        TransactionStratum savTransactionStratum = transactionStratumRepository.save(newTransactionStratum);

        TransactionStratum transactionStratum = transactionStratumRepository
                .findByReferenceTransactionId(newTransactionStratum.getReferenceTransactionId()).get();

        assertEquals(savTransactionStratum, transactionStratum);

    }

}
