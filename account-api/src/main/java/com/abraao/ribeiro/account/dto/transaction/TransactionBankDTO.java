package com.abraao.ribeiro.account.dto.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransactionBankDTO {

    @ApiModelProperty(hidden = true)
    private String name;

    private String number;
}
