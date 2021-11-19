package com.abraao.ribeiro.payment.mapper;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionStratumMapper {

    TransactionStratum toModel(TransactionDTO dto);
}
