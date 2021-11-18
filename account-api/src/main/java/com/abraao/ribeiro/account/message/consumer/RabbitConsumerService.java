package com.abraao.ribeiro.account.message.consumer;

import com.abraao.ribeiro.account.dto.InfoTransactionStratum;
import com.abraao.ribeiro.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import static com.abraao.ribeiro.account.constant.RabbitConstant.QUEEU_TRANSACTION;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitConsumerService {

    protected final PlatformTransactionManager transactionManager;

    private final AccountService accountService;

    @RabbitHandler
    @RabbitListener(queues = {QUEEU_TRANSACTION})
    public void getInfoTransactionStratum (String message) throws JsonProcessingException {
        InfoTransactionStratum infoTransactionStratum = new ObjectMapper().readValue(message, InfoTransactionStratum.class);
        log.info("Messagem recebida. Conta target para realizar atualização dos valores {}", infoTransactionStratum);
        accountService.updateAccountBalaceTarget(infoTransactionStratum);
    }

}
