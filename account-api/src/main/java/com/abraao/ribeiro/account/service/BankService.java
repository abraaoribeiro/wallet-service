package com.abraao.ribeiro.account.service;

import com.abraao.ribeiro.account.exception.EntityNotFaundException;
import com.abraao.ribeiro.account.model.Bank;
import com.abraao.ribeiro.account.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public Bank findOrFail(Long id){
        return bankRepository.findById(id).orElseThrow(() -> new EntityNotFaundException("O banco informado não está cadastrado"));
    }

}
