package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.exception.BankNotFoundException;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public Bank findOrFail(String number){
        return bankRepository.findByNumber(number).orElseThrow(() -> new BankNotFoundException(number));
    }

}
