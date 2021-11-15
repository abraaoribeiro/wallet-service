package com.abraao.ribeiro.payment.controller;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.model.TransactionStratum;
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

    public List<TransactionStratum> findAll() {
        return transactionRepository.findAll();
    }

    @PostMapping("transation-type/{transactionType}/transfer-type/{transferType}")
    public TransactionStratum transaction(@PathVariable TransactionType transactionType, @PathVariable TransferType transferType,
                                          @RequestBody TransactionDTO transactionDTO) {

        return transactionService.transaction(transactionType,transferType, transactionDTO);
    }

}
