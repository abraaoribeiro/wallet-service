package com.abraao.ribeiro.payment.exception;

import javax.persistence.EntityNotFoundException;

public class TransactionStratumNotFoundException extends EntityNotFoundException {

    public TransactionStratumNotFoundException(String referenceTransactionId){
        super(String.format("Extrato da transação não encontrado %s", referenceTransactionId));
    }
}
