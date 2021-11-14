package com.abraao.ribeiro.payment.controller;

import com.abraao.ribeiro.payment.dto.AccountDTO;
import com.abraao.ribeiro.payment.model.Transaction;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.model.enums.TransferType;
import com.abraao.ribeiro.payment.repository.TransactionRepository;
import com.abraao.ribeiro.payment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final TransactionRepository transactionRepository;

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    @PostMapping("transfer-type/{transferType}/transation-type/{transactionType}")
    public Transaction transaction(@PathVariable TransferType transferType, @PathVariable TransactionType transactionType,
                                   @RequestBody AccountDTO accountDTO){

        return transactionService.transaction(transferType,transactionType,accountDTO) ;
    }
}
