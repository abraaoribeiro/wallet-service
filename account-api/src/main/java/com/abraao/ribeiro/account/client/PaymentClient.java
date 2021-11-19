package com.abraao.ribeiro.account.client;

import com.abraao.ribeiro.account.dto.TransactionDTO;
import com.abraao.ribeiro.account.dto.TransactionStratumDTO;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payment")
public interface PaymentClient {

    @PostMapping("/transactions/{transactionType}")
    TransactionStratumDTO createTransaction(@PathVariable TransactionType transactionType, @RequestBody TransactionDTO infoTransactionDTO);

    @GetMapping("/transactions/{referenceTransactionId}")
    TransactionStratumDTO findTransactionByReferenceId(@PathVariable String referenceTransactionId);
}
