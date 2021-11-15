package com.abraao.ribeiro.account.exception;

public class EntityNotFaundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntityNotFaundException(String message){
        super(message);
    }
}
