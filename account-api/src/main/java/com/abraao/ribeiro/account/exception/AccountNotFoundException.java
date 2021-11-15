package com.abraao.ribeiro.account.exception;

public class AccountNotFoundException extends EntityNotFaundException{

    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Long id){
        this(String.format("Não existe um casdastro da conta com o id %d",id));
    }
}
