package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.dto.account.AccountDTO;
import com.abraao.ribeiro.account.dto.account.AddressDTO;
import com.abraao.ribeiro.account.dto.account.BankDTO;
import com.abraao.ribeiro.account.dto.account.ClientDTO;
import com.abraao.ribeiro.account.exception.BankNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountServiceTest {

    @MockBean
    private AccountService accountService;

    @Test
    void given_Account_How_Bank_Null_when_Create_then_Save_Account_Return_NotFoundException() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setNumber("52");
        addressDTO.setZipCode("658100");
        addressDTO.setCity("Belém");
        addressDTO.setComplement("Px ao super");
        addressDTO.setDistrict("Guanabara");
        addressDTO.setPlace("Passagem maria");
        addressDTO.setState("Pará");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Maria");
        clientDTO.setCpf("041-520-648-96");
        clientDTO.setAddress(addressDTO);

        BankDTO bankDTO = new BankDTO();
        bankDTO.setNumber("45555");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(new BigDecimal("500"));
        accountDTO.setBank(bankDTO);
        accountDTO.setClient(clientDTO);

        when(accountService.create(accountDTO)).thenThrow(new BankNotFoundException(bankDTO.getNumber()));

    }
}
