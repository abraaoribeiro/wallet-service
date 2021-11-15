package com.abraao.ribeiro.client.external.client;

import com.abraao.ribeiro.client.dto.InfoAccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("account")
public interface AccountResource {

    @PostMapping("/accounts")
    InfoAccountDTO createAccount(InfoAccountDTO accountDTO);

}
