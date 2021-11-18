package com.abraao.ribeiro.payment.service;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.message.producer.TransactionProducerService;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionProducerService transactionProducerService;

    public TransactionStratum createTransaction(TransactionDTO transactionDTO) {

        checkBalanceAccount(transactionDTO);

        var sourceBalance = transactionDTO.getAccountSource().getBalance().subtract(transactionDTO.getValue());
        var targetBalace = transactionDTO.getAccountTarget().getBalance().add(transactionDTO.getValue());

        //TODO refatorar para mapStruct e setar o tipo da transação
        TransactionStratum transactionStratumSource = TransactionStratum.builder()
                .transactionType(transactionDTO.getTransactionType())
                .referenceTransactionId(transactionDTO.getAccountSource().getReferenceTransactionId())
                .transferredValue(transactionDTO.getValue())
                .previousBalance(transactionDTO.getAccountSource().getBalance())
                .currentBalance(sourceBalance)
                .clientCpfSource(transactionDTO.getAccountSource().getClient().getCpf())
                .clientNameSource(transactionDTO.getAccountSource().getClient().getName())
                .bankNumberSource(transactionDTO.getAccountSource().getBank().getNumber())
                .bankNameSource(transactionDTO.getAccountSource().getBank().getName())
                .clientCpfSource(transactionDTO.getAccountTarget().getClient().getCpf())
                .clientNameSource(transactionDTO.getAccountTarget().getClient().getName())
                .bankNumberSource(transactionDTO.getAccountTarget().getBank().getNumber())
                .bankNameSource(transactionDTO.getAccountTarget().getBank().getName())
                .clientCpfTarget(transactionDTO.getAccountTarget().getClient().getCpf())
                .clientNameTarget(transactionDTO.getAccountTarget().getClient().getName())
                .bankNumberTarget(transactionDTO.getAccountTarget().getBank().getNumber())
                .bankNameTarget(transactionDTO.getAccountTarget().getBank().getName())
                .clientCpfTarget(transactionDTO.getAccountSource().getClient().getCpf())
                .clientNameTarget(transactionDTO.getAccountSource().getClient().getName())
                .bankNumberTarget(transactionDTO.getAccountSource().getBank().getNumber())
                .bankNameTarget(transactionDTO.getAccountSource().getBank().getName())
                .build();

        transactionRepository.save(transactionStratumSource);

        //TODO refatorar para mapStruct
        TransactionStratum transactionStratumTarget = TransactionStratum.builder()
                .transactionType(transactionDTO.getTransactionType())
                .referenceTransactionId(transactionDTO.getAccountTarget().getReferenceTransactionId())
                .transferredValue(transactionDTO.getValue())
                .previousBalance(transactionDTO.getAccountTarget().getBalance())
                .currentBalance(targetBalace)
                .clientCpfTarget(transactionDTO.getAccountTarget().getClient().getCpf())
                .clientNameTarget(transactionDTO.getAccountTarget().getClient().getName())
                .bankNumberTarget(transactionDTO.getAccountTarget().getBank().getNumber())
                .bankNameTarget(transactionDTO.getAccountTarget().getBank().getName())
                .clientCpfTarget(transactionDTO.getAccountSource().getClient().getCpf())
                .clientNameTarget(transactionDTO.getAccountSource().getClient().getName())
                .bankNumberTarget(transactionDTO.getAccountSource().getBank().getNumber())
                .bankNameTarget(transactionDTO.getAccountSource().getBank().getName())
                .clientCpfSource(transactionDTO.getAccountSource().getClient().getCpf())
                .clientNameSource(transactionDTO.getAccountSource().getClient().getName())
                .bankNumberSource(transactionDTO.getAccountSource().getBank().getNumber())
                .bankNameSource(transactionDTO.getAccountSource().getBank().getName())
                .clientCpfSource(transactionDTO.getAccountTarget().getClient().getCpf())
                .clientNameSource(transactionDTO.getAccountTarget().getClient().getName())
                .bankNumberSource(transactionDTO.getAccountTarget().getBank().getNumber())
                .bankNameSource(transactionDTO.getAccountTarget().getBank().getName())
                .build();

        transactionRepository.save(transactionStratumTarget);

        //TODO separar para um metodo
        transactionDTO.getAccountSource().setBalance(sourceBalance);
        transactionDTO.getAccountTarget().setBalance(targetBalace);

        //TODO colocar a transação Target na fila;
        transactionProducerService.sendTransation(transactionStratumTarget);

       // if (transactionDTO.getTransactionType().equals(TransactionType.DOC)) {
         //   transactionProducerService.sendTransation(transactionDTO);
       // }

        return transactionStratumSource;
    }

    private void checkBalanceAccount(TransactionDTO transactionDTO) {
        var hasBalance = transactionDTO.getAccountSource().getBalance().compareTo(transactionDTO.getValue());
        Assert.isTrue(hasBalance >= 0, "Você não possui saldo para essa transação");
    }

    public TransactionStratum findTransactionByReferenceId(String referenceTransactionId) {
        return transactionRepository.findByReferenceTransactionId(referenceTransactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    }
}
