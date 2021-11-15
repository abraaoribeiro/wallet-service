package com.abraao.ribeiro.payment.external.client;

import com.abraao.ribeiro.payment.dto.InfoAccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;

@FeignClient("account")
public interface AccountResource {

    @GetMapping("accounts/reference-client/{referenceClientId}")
    InfoAccountDTO getAccountByReferenceClietId(@PathVariable String referenceClientId);

    @PutMapping("/{accountId}/balance")
    InfoAccountDTO updateBalanceAccount(@PathVariable Long accountId, @RequestBody BigDecimal balance);

}
