package com.abraao.ribeiro.payment.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {

    private String name;

    private String cpf;

}
