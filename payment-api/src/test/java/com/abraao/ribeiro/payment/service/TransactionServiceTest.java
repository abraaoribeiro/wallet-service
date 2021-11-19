package com.abraao.ribeiro.payment.service;

import com.abraao.ribeiro.payment.dto.AccountDTO;
import com.abraao.ribeiro.payment.dto.BankDTO;
import com.abraao.ribeiro.payment.dto.ClientDTO;
import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.exception.TransactionStratumNotFoundException;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TransactionServiceTest {

    @MockBean
    public TransactionService transactionService;

    @Test
    void given_TransactionDTO_WhenCreateTransaction_Then_Return_TransactionStratum_Check_NotNull() {
        BankDTO bankSourceDTO = new BankDTO();
        bankSourceDTO.setName("Banco do Brasil");
        bankSourceDTO.setNumber("522");

        ClientDTO clientSourceDTO = new ClientDTO();
        clientSourceDTO.setCpf("640.489.460-21");
        clientSourceDTO.setName("João da Silva");

        AccountDTO accountSourceDTO = new AccountDTO();
        accountSourceDTO.setNumber("00001");
        accountSourceDTO.setAgency("001");
        accountSourceDTO.setBank(bankSourceDTO);
        accountSourceDTO.setClient(clientSourceDTO);
        accountSourceDTO.setBalance(new BigDecimal("5000"));
        accountSourceDTO.setReferenceTransactionId(UUID.randomUUID().toString());

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setValue(new BigDecimal("50"));
        transactionDTO.setAccountSource(accountSourceDTO);

        transactionDTO.getAccountSource().setBalance(accountSourceDTO.getBalance());
        transactionDTO.getAccountSource().getBank().setName(accountSourceDTO.getBank().getName());
        transactionDTO.getAccountSource().getBank().setNumber(accountSourceDTO.getBank().getNumber());
        transactionDTO.getAccountSource().setReferenceTransactionId(UUID.randomUUID().toString());
        transactionDTO.getAccountSource().getClient().setName(accountSourceDTO.getClient().getName());
        transactionDTO.getAccountSource().getClient().setCpf(accountSourceDTO.getClient().getCpf());

        BankDTO bankTargetDTO = new BankDTO();
        bankTargetDTO.setName("Banco do Brasil");
        bankTargetDTO.setNumber("522");

        ClientDTO clientTargetDTO = new ClientDTO();
        clientTargetDTO.setCpf("640.489.460-21");
        clientTargetDTO.setName("João da Silva");

        AccountDTO accountTargetDTO = new AccountDTO();
        accountTargetDTO.setNumber("00001");
        accountTargetDTO.setAgency("001");
        accountTargetDTO.setBank(bankTargetDTO);
        accountTargetDTO.setClient(clientTargetDTO);
        accountTargetDTO.setBalance(new BigDecimal("5000"));
        accountTargetDTO.setReferenceTransactionId(UUID.randomUUID().toString());
        transactionDTO.setAccountTarget(accountTargetDTO);

        transactionDTO.getAccountTarget().setBalance(accountTargetDTO.getBalance());
        transactionDTO.getAccountTarget().getBank().setName(accountTargetDTO.getBank().getName());
        transactionDTO.getAccountTarget().getBank().setNumber(accountTargetDTO.getBank().getNumber());
        transactionDTO.getAccountTarget().setReferenceTransactionId(UUID.randomUUID().toString());
        transactionDTO.getAccountTarget().getClient().setName(accountTargetDTO.getClient().getName());
        transactionDTO.getAccountTarget().getClient().setCpf(accountTargetDTO.getClient().getCpf());
        transactionDTO.setTransactionType(TransactionType.DEPOSIT);

        TransactionStratum transactionStratum = new TransactionStratum();
        when(transactionService.createTransaction(transactionDTO)).thenReturn(transactionStratum);
        assertNotNull(transactionStratum);

    }


    @Test
    void given_TransactionStratum_when_Null_then_NotFoundException() throws Exception {
        var referenceTransactionId = UUID.randomUUID().toString();
        TransactionStratum transactionStratum = new TransactionStratum();
        transactionStratum.setReferenceTransactionId(referenceTransactionId);

        when(transactionService.findTransactionByReferenceId(referenceTransactionId))
                .thenThrow(new TransactionStratumNotFoundException(referenceTransactionId));
    }
}
