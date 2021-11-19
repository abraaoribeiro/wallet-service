package com.abraao.ribeiro.account.controller;

import com.abraao.ribeiro.account.dto.account.AccountDTO;
import com.abraao.ribeiro.account.dto.account.AddressDTO;
import com.abraao.ribeiro.account.dto.account.BankDTO;
import com.abraao.ribeiro.account.dto.account.ClientDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void given_AccountDTO_whenCreateAccount_then_Return_HttpStatus_Create() throws Exception {
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
        bankDTO.setName("Itáu");
        bankDTO.setNumber("522");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(new BigDecimal("500"));
        accountDTO.setBank(bankDTO);
        accountDTO.setClient(clientDTO);

        String json = new ObjectMapper().writeValueAsString(accountDTO);

        when(accountService.create(accountDTO)).thenReturn(new Account());

        mockMvc.perform(post("/accounts/create")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }


}
