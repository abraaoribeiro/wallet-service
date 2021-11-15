package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.dto.AccountDTO;
import com.abraao.ribeiro.account.exception.AccountNotFoundException;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final BankService bankService;

    public Account create(AccountDTO accountDTO) {
        return accountRepository.save(createAccountBuilder(accountDTO).build());
    }

    public Account update(Long accountId, AccountDTO accountDTO){
        Account account = findByIdOrFail(accountId);
        BeanUtils.copyProperties(accountDTO, account, "id");
        return accountRepository.save(account);
    }

    public Account findByIdOrFail(Long accountId) {
       return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public Account findReferenceClientIdOrFail(String referenceClientId) {
        return accountRepository.findByReferenceClientId(referenceClientId)
                .orElseThrow(() -> new AccountNotFoundException(referenceClientId));
    }

    private Account.AccountBuilder createAccountBuilder(AccountDTO accountDTO){
        Bank bank = bankService.findOrFail(accountDTO.getBankId());

        return Account.builder()
                .agency(accountDTO.getAgency())
                .balance(accountDTO.getBalace())
                .number(accountDTO.getNumber())
                .bank(bank)
                .referenceClientId(accountDTO.getReferenceClientId());
    }

}
