package com.abraao.ribeiro.account.mapper;

import com.abraao.ribeiro.account.dto.AccountDTO;
import com.abraao.ribeiro.account.dto.AddressDTO;
import com.abraao.ribeiro.account.dto.ClientDTO;
import com.abraao.ribeiro.account.model.Account;
import com.abraao.ribeiro.account.model.Address;
import com.abraao.ribeiro.account.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AccountMapper {

    Account toModel(AccountDTO accountDTO);

    Client toMapClient(ClientDTO clientDTO);

    Address toMapAddress(AddressDTO addressDTO);
}
