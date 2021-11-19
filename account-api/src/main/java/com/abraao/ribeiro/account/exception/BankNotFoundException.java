package com.abraao.ribeiro.account.exception;

import javax.persistence.EntityNotFoundException;

public class BankNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public BankNotFoundException(String message) {
        super(message);
    }

    BankNotFoundException(Long id){
        this(String.format("Não existe um banco com código %d",id));
    }
}
