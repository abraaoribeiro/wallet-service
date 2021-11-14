package com.abraao.ribeiro.payment.service;

import com.abraao.ribeiro.payment.dto.AccountDTO;
import com.abraao.ribeiro.payment.model.Account;
import com.abraao.ribeiro.payment.model.Transaction;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.model.enums.TransferType;
import com.abraao.ribeiro.payment.repository.AccountRepository;
import com.abraao.ribeiro.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;


    public Transaction transaction(TransferType transferType, TransactionType transactionType, AccountDTO accountDTO) {

        Account account = new Account();

        if(TransferType.DOC.equals(transferType)){
            //TODO utilizar  a fila
        }

        //TODO verificar se tem saldo suficiente na conta;
        var valueCalculation = accountDTO.getAccountSource().getBalace().subtract(accountDTO.getValue());
        accountDTO.getAccountTarget().getBalace().add(accountDTO.getValue());

        //TODO buscar a conta target no banco de tados.

        account.setBalace(valueCalculation);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);

        return transactionRepository.save(transaction);
    }




}
