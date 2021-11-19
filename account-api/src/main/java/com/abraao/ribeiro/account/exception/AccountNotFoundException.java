package com.abraao.ribeiro.account.exception;

public class AccountNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(String cpf) {
        super(String.format("Não existe um casdastro de Client com o cpf %s", cpf));
    }

}
