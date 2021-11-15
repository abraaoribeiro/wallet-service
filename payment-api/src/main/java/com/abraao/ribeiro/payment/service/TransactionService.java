package com.abraao.ribeiro.payment.service;

import com.abraao.ribeiro.payment.dto.InfoAccountDTO;
import com.abraao.ribeiro.payment.dto.InfoClientDTO;
import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.external.client.AccountResource;
import com.abraao.ribeiro.payment.external.client.ClientResource;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.abraao.ribeiro.payment.model.enums.TransactionType;
import com.abraao.ribeiro.payment.model.enums.TransferType;
import com.abraao.ribeiro.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountResource accountResource;

    private final ClientResource clientResource;

    public TransactionStratum transaction(TransactionType transactionType, TransferType transferType,
                                          TransactionDTO transactionDTO) {

        InfoAccountDTO accountSource = getAccountByClientReferenceId(transactionDTO.getAccountSource());
        InfoAccountDTO accountTarget = getAccountByClientReferenceId(transactionDTO.getAccountTarget());

        if (TransferType.DOC.equals(transferType)) {
            //TODO utilizar  a fila
        }

        if (hasBalance(accountSource.getBalance(), transactionDTO.getValue())) {
            var balanceSource = accountSource.getBalance().subtract(transactionDTO.getValue());
            var balanceTarget = accountTarget.getBalance().add(transactionDTO.getValue());

            accountResource.updateBalanceAccount(accountSource.getId(),balanceSource);
            accountResource.updateBalanceAccount(accountTarget.getId(),balanceTarget);
        }

        //TODO gerar o estrato da transação
        TransactionStratum transactionStratum = new TransactionStratum();
        transactionStratum.setTransactionType(transactionType);

        return transactionRepository.save(transactionStratum);
    }

    private boolean hasBalance(BigDecimal balance, BigDecimal value) {
        return (balance.compareTo(BigDecimal.valueOf(value.doubleValue())) > 0);
    }

    private InfoAccountDTO getAccountByClientReferenceId(InfoAccountDTO accountDTO) {
        InfoClientDTO client = clientResource.getClientByCpf(accountDTO.getClient().getCpf());
        return accountResource.getAccountByReferenceClietId(client.getReferenceId());
    }

}
