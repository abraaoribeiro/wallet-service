package com.abraao.ribeiro.client.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddresDTO {

    @NotBlank(message = "O campo zipCode não pode ser nulo ou vazio")
    private String zipCode;

    @NotBlank(message = "O campo place não pode ser nulo ou vazio")
    private String place;

    @NotBlank(message = "O campo number não pode ser nulo ou vazio")
    private String number;

    @NotBlank(message = "O campo complemente não pode ser nulo ou vazio")
    private String complement;

    @NotBlank(message = "O campo district não pode ser nulo ou vazio")
    private String district;
}
