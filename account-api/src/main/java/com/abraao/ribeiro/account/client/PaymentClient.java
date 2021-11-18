package com.abraao.ribeiro.account.client;

import com.abraao.ribeiro.account.dto.InfoTransactionDTO;
import com.abraao.ribeiro.account.dto.InfoTransactionStratum;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payment")
public interface PaymentClient {

    @PostMapping("/transactions/{transactionType}")
    InfoTransactionStratum createTransaction(@PathVariable TransactionType transactionType, @RequestBody InfoTransactionDTO infoTransactionDTO);

    @GetMapping("/transactions/{referenceTransactionId}")
    InfoTransactionStratum findTransactionByReferenceId(@PathVariable String referenceTransactionId);
}
