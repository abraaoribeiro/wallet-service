package com.abraao.ribeiro.account.controller;

import com.abraao.ribeiro.account.dto.account.AccountDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionResponseDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionStratumListDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import com.abraao.ribeiro.account.openapi.controller.AccountControllerOpenApi;
import com.abraao.ribeiro.account.service.AccountService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Api(tags = "Account")
public class AccountController implements AccountControllerOpenApi {

    private final AccountService accountService;

    @GetMapping("/transactions")
    public List<TransactionStratumListDTO> findAllTransactionStratum(){
        return accountService.findAllTransactionStratum();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @PostMapping("/{transactionType}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO createTransaction(@Valid @PathVariable TransactionType transactionType, @RequestBody TransactionDTO transactionDTO){
        return accountService.createTransaction(transactionType,transactionDTO);
    }

}
