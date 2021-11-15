package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BankDTO {

    private String number;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
