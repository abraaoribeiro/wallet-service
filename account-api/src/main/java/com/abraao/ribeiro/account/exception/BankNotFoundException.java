package com.abraao.ribeiro.account.exception;


public class BankNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public BankNotFoundException(String number) {
        super(String.format("Não existe um banco com código %s",number));
    }

}
