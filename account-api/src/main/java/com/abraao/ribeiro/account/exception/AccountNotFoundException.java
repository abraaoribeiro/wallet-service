package com.abraao.ribeiro.account.exception;

public class AccountNotFoundException extends EntityNotFaundException{

    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);
    }

}
