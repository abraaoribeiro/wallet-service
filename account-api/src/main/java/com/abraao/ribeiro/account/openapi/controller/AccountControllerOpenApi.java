package com.abraao.ribeiro.account.openapi.controller;

import com.abraao.ribeiro.account.dto.account.AccountDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionDTO;
import com.abraao.ribeiro.account.dto.transaction.TransactionResponseDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.enums.TransactionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Account")
public interface AccountControllerOpenApi {

    @ApiOperation(value = "Cadastra uma conta")
    @ApiResponses(@ApiResponse(code = 201, message = "Conta cadastrada"))
    Account create(@ApiParam(name = "corpo", value = "Representação de uma nova conta", required = true)
                    AccountDTO account);


    @ApiOperation("Cria uma transação")
    @ApiResponses(@ApiResponse(code = 201, message = "Transação criada"))
    TransactionResponseDTO createTransaction(@ApiParam(name = "corpo", value = "Representação de uma nova transação", required = true)
                    TransactionType transactionType, TransactionDTO transactionDTO);
}
