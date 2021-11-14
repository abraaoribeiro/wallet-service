package com.abraao.ribeiro.payment.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BankDTO {

    private String namber;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;
}
