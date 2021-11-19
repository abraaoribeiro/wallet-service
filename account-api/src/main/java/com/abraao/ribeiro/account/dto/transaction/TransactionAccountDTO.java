package com.abraao.ribeiro.account.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionAccountDTO {

    @ApiModelProperty(hidden = true)
    private String referenceTransactionId;

    private String number;

    private String agency;

    private BigDecimal balance;

    private TransactionBankDTO bank;

    private TransationClientDTO client;
}
