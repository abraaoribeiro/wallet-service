package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.client.PaymentClient;
import com.abraao.ribeiro.account.dto.account.AccountDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionResponseDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionStratumDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionStratumListDTO;
import com.abraao.ribeiro.account.exception.AccountNotFoundException;
import com.abraao.ribeiro.account.mapper.AccountMapper;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.abraao.ribeiro.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BankService bankService;

    private final PaymentClient paymentClient;

    private final AccountMapper accountMapper;

    @Transactional
    public Account create(AccountDTO accountDTO) {
        return accountRepository.save(mapAccount(accountDTO));
    }


    @Transactional
    public void updateAccountBalaceSource(TransactionStratumDTO transactionStratumDTO) {
        Account accountSource = findCpfOrFail(transactionStratumDTO.getAccountSource().getClient().getCpf());
        var accountSourceBalance = transactionStratumDTO.getCurrentBalance();
        accountRepository.updateBalance(accountSourceBalance, accountSource.getId());
    }

    @Transactional
    public void updateAccountBalaceTarget(TransactionStratumDTO transactionStratumDTO) {
        Account accountTarget = findCpfOrFail(transactionStratumDTO.getAccountTarget().getClient().getCpf());
        var accountTargetBalance = transactionStratumDTO.getCurrentBalance();
        accountRepository.updateBalance(accountTargetBalance, accountTarget.getId());
    }

    public Account findCpfOrFail(String cpf) {
        return accountRepository.findByClientCpf(cpf)
                .orElseThrow(() -> new AccountNotFoundException(cpf));
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionType transactionType, TransactionDTO transactionDTO) {
        Account accountSource = findCpfOrFail(transactionDTO.getAccountSource().getClient().getCpf());
        Account accountTarget = findCpfOrFail(transactionDTO.getAccountTarget().getClient().getCpf());

        mapTransactionSourceAndTarget(transactionType, transactionDTO, accountSource, accountTarget);

        TransactionStratumDTO transactionStratum = paymentClient.createTransaction(transactionType, transactionDTO);
        updateAccountBalaceSource(transactionStratum);

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setReferenceTransactionId(transactionStratum.getReferenceTransactionId());

        return transactionResponseDTO;
    }

    private void mapTransactionSourceAndTarget(TransactionType transactionType, TransactionDTO transactionDTO, Account accountSource, Account accountTarget) {
        transactionDTO.setTransactionType(transactionType);
        transactionDTO.getAccountSource().setBalance(accountSource.getBalance());
        transactionDTO.getAccountSource().getBank().setName(accountSource.getBank().getName());
        transactionDTO.getAccountSource().setReferenceTransactionId(UUID.randomUUID().toString());

        transactionDTO.getAccountTarget().setReferenceTransactionId(UUID.randomUUID().toString());
        transactionDTO.getAccountTarget().setBalance(accountTarget.getBalance());
        transactionDTO.getAccountTarget().getBank().setName(accountTarget.getBank().getName());
    }

    private Account mapAccount(AccountDTO accountDTO) {
        Bank bank = bankService.findOrFail(accountDTO.getBank().getNumber());

        Account account = accountMapper.toModel(accountDTO);
        account.setReferenceTransactionId(UUID.randomUUID().toString());
        account.setBank(bank);
        return account;
    }

    public List<TransactionStratumListDTO> findAllTransactionStratum() {
        return paymentClient.findAll();
    }
}
