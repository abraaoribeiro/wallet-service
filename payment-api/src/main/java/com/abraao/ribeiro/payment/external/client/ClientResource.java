package com.abraao.ribeiro.payment.external.client;

import com.abraao.ribeiro.payment.dto.InfoClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("client")
public interface ClientResource {

    @GetMapping("clients/{cpf}")
    InfoClientDTO getClientByCpf(@PathVariable String cpf);

}
