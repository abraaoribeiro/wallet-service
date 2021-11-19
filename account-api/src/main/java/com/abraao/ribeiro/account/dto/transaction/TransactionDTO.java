package com.abraao.ribeiro.account.dto.transaction;

import com.abraao.ribeiro.account.model.enums.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransactionDTO implements Serializable {

    @ApiModelProperty(position = 1)
    private BigDecimal value;

    @ApiModelProperty(hidden = true)
    private TransactionType transactionType;

    private TransactionAccountDTO accountSource;

    private TransactionAccountDTO accountTarget;
}
