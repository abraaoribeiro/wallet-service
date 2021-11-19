package com.abraao.ribeiro.payment.service;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.exception.TransactionStratumNotFoundException;
import com.abraao.ribeiro.payment.mapper.TransactionStratumMapper;
import com.abraao.ribeiro.payment.message.producer.TransactionProducerService;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.repository.TransactionStratumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionStratumRepository transactionRepository;

    private final TransactionProducerService transactionProducerService;

    private final TransactionStratumMapper transactionStratumMapper;


    public TransactionStratum findTransactionByReferenceId(String referenceTransactionId) {
        return transactionRepository.findByReferenceTransactionId(referenceTransactionId)
                .orElseThrow(() -> new TransactionStratumNotFoundException(referenceTransactionId));
    }

    public List<TransactionStratum> findAll(){
        return transactionRepository.findAll();
    }

    @Transactional
    public TransactionStratum createTransaction(TransactionDTO transactionDTO) {
        checkBalanceAccount(transactionDTO);

        TransactionStratum transactionStratumSource = createTransactionAccountSource(transactionDTO);

        if(!transactionDTO.getTransactionType().equals(TransactionType.WITHDRAW)){
            TransactionStratum transactionStratumTarget = createTransactionAccountTarget(transactionDTO);
            transactionProducerService.sendTransation(transactionStratumTarget);
        }

        return transactionStratumSource;
    }

    private TransactionStratum createTransactionAccountSource(TransactionDTO transactionDTO) {
        BigDecimal sourceBalance = subtractBalanceAccountSource(transactionDTO);
        TransactionStratum transactionStratumSource = transactionStratumMapper.toModel(transactionDTO);
        transactionStratumSource.setReferenceTransactionId(transactionDTO.getAccountSource().getReferenceTransactionId());
        transactionStratumSource.setPreviousBalance(transactionDTO.getAccountSource().getBalance());
        transactionStratumSource.setTransferredValue(transactionDTO.getValue());
        transactionStratumSource.setCurrentBalance(sourceBalance);
        transactionRepository.save(transactionStratumSource);
        return transactionStratumSource;
    }

    private TransactionStratum createTransactionAccountTarget(TransactionDTO transactionDTO) {
        BigDecimal targetBalace = sumBalanceAccountTarget(transactionDTO);
        TransactionStratum transactionStratumTarget = transactionStratumMapper.toModel(transactionDTO);
        transactionStratumTarget.setReferenceTransactionId(transactionDTO.getAccountTarget().getReferenceTransactionId());
        transactionStratumTarget.setPreviousBalance(transactionDTO.getAccountTarget().getBalance());
        transactionStratumTarget.setTransferredValue(transactionDTO.getValue());
        transactionStratumTarget.setCurrentBalance(targetBalace);
        transactionRepository.save(transactionStratumTarget);
        return transactionStratumTarget;
    }

    private BigDecimal sumBalanceAccountTarget(TransactionDTO transactionDTO) {
        return transactionDTO.getAccountTarget().getBalance().add(transactionDTO.getValue());
    }

    private BigDecimal subtractBalanceAccountSource(TransactionDTO transactionDTO) {
        return transactionDTO.getAccountSource().getBalance().subtract(transactionDTO.getValue());
    }

    private void checkBalanceAccount(TransactionDTO transactionDTO) {
        var hasBalance = transactionDTO.getAccountSource().getBalance().compareTo(transactionDTO.getValue());
        Assert.isTrue(hasBalance >= 0, "Você não possui saldo para essa transação");
    }
}
