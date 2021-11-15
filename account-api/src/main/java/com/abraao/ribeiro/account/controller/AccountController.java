package com.abraao.ribeiro.account.controller;

import com.abraao.ribeiro.account.dto.AccountDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody AccountDTO accountDTO){
        return accountService.create(accountDTO);
    }

    @GetMapping("reference-client/{referenceClientId}")
    public Account findByClientId(@PathVariable String referenceClientId){
        return accountService.findReferenceClientIdOrFail(referenceClientId);
    }

    @GetMapping("/{accountId}")
    public Account findById(@PathVariable Long accountId){
      return  accountService.findByIdOrFail(accountId);
    }

    @PutMapping("/{accountId}")
    public Account update(@PathVariable Long accountId, @RequestBody AccountDTO accountDTO){
        return accountService.update(accountId,accountDTO);
    }



}
