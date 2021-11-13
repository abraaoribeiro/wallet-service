package com.abraao.ribeiro.client.exception;

public class ClientNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(Long id){
        this(String.format("Não existe um casdastro de cliente com o id %d",id));
    }
}
