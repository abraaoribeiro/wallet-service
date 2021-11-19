package com.abraao.ribeiro.account.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado"),
    INTERNAL_ERROR("/internal-error", "Erro no servidor");

    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "" + path;
        this.title = title;
    }
}
