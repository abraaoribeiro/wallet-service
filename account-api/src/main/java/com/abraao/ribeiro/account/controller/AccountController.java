package com.abraao.ribeiro.account.controller;

import com.abraao.ribeiro.account.dto.AccountDTO;
import com.abraao.ribeiro.account.dto.InfoTransactionDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.abraao.ribeiro.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @PostMapping("/{transactionType}")
    public ResponseEntity<?> transaction(@Valid @PathVariable TransactionType transactionType, @RequestBody InfoTransactionDTO infoTransactionDTO){
        accountService.createTransaction(transactionType,infoTransactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
