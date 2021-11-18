package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.client.PaymentClient;
import com.abraao.ribeiro.account.dto.AccountDTO;
import com.abraao.ribeiro.account.dto.InfoTransactionDTO;
import com.abraao.ribeiro.account.dto.InfoTransactionStratum;
import com.abraao.ribeiro.account.exception.AccountNotFoundException;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.abraao.ribeiro.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BankService bankService;

    private final PaymentClient paymentClient;

    @Transactional
    public Account create(AccountDTO accountDTO) {
        return accountRepository.save(createAccountBuilder(accountDTO).build());
    }

    @Transactional
    public void createTransaction(TransactionType transactionType, InfoTransactionDTO infoTransactionDTO) {
        Account accountSource = findCpfOrFail(infoTransactionDTO.getAccountSource().getClient().getCpf());
        Account accountTarget = findCpfOrFail(infoTransactionDTO.getAccountTarget().getClient().getCpf());

        //TODO refatorar para mapStruct
        infoTransactionDTO.setTransactionType(transactionType);

        infoTransactionDTO.getAccountSource().setBalance(accountSource.getBalance());
        infoTransactionDTO.getAccountSource().getBank().setName(accountSource.getBank().getName());
        infoTransactionDTO.getAccountSource().setReferenceTransactionId(UUID.randomUUID().toString());

        infoTransactionDTO.getAccountTarget().setReferenceTransactionId(UUID.randomUUID().toString());
        infoTransactionDTO.getAccountTarget().setBalance(accountTarget.getBalance());
        infoTransactionDTO.getAccountTarget().getBank().setName(accountTarget.getBank().getName());

        InfoTransactionStratum transactionStratum = paymentClient.createTransaction(transactionType, infoTransactionDTO);

        //TODO ver um nome melhor para o referenciaTransacaoId
        updateAccountBalaceSource(transactionStratum);
        //TODO retornar o comprovante
    }

    //TODO Refatorar o nome do metodo
    @Transactional
    public void updateAccountBalaceSource(InfoTransactionStratum infoTransactionStratum) {
        Account accountSource = findCpfOrFail(infoTransactionStratum.getClientCpfSource());
        var accountSourceBalance = infoTransactionStratum.getCurrentBalance();
        accountRepository.updateBalance(accountSourceBalance, accountSource.getId());
    }

    @Transactional
    public void updateAccountBalaceTarget(InfoTransactionStratum infoTransactionStratum){
        Account accountTarget = findCpfOrFail(infoTransactionStratum.getClientCpfTarget());
        var accountTargetBalance = infoTransactionStratum.getCurrentBalance();
        accountRepository.updateBalance(accountTargetBalance, accountTarget.getId());
    }

    public Account findCpfOrFail(String cpf) {
        return accountRepository.findByCpf(cpf)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Não existe um casdastro de Client com o cpf %d", cpf)));
    }

    private Account.AccountBuilder createAccountBuilder(AccountDTO accountDTO) {
        Bank bank = bankService.findOrFail(accountDTO.getBank().getNumber());

        return Account.builder()
                .agency(accountDTO.getAgency())
                .numberAccount(accountDTO.getNumber())
                .balance(accountDTO.getBalance())
                .name(accountDTO.getClient().getName())
                .cpf(accountDTO.getClient().getCpf())
                .zipCode(accountDTO.getClient().getAddress().getZipCode())
                .number(accountDTO.getClient().getAddress().getNumber())
                .complement(accountDTO.getClient().getAddress().getComplement())
                .district(accountDTO.getClient().getAddress().getDistrict())
                .place(accountDTO.getClient().getAddress().getPlace())
                .city(accountDTO.getClient().getAddress().getCity())
                .state(accountDTO.getClient().getAddress().getState())
                .referenceTransactionId(UUID.randomUUID().toString())
                .bank(bank);
    }

}
