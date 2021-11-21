package com.abraao.ribeiro.payment.controller;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping()
    public List<TransactionStratum> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{referenceTransactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionStratum findTransactionByReferenceId(@PathVariable String referenceTransactionId){
        return transactionService.findTransactionByReferenceId(referenceTransactionId);
    }

    @PostMapping("/{transactionType}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionStratum createTransaction(@PathVariable TransactionType transactionType, @RequestBody TransactionDTO transactionDTO) {
        return transactionService.createTransaction(transactionDTO);
    }



}
