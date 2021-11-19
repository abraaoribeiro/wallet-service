package com.abraao.ribeiro.account.dto.account;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class AddressDTO {

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

    @NotBlank(message = "O campo city não pode ser nulo ou vazio")
    private String city;

    @NotBlank(message = "O campo state não pode ser nulo ou vazio")
    private String state;

}
