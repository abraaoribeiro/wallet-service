package com.abraao.ribeiro.payment.message.producer;

import com.abraao.ribeiro.payment.dto.TransactionDTO;
import com.abraao.ribeiro.payment.model.TransactionStratum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.abraao.ribeiro.payment.constant.RabbitConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionProducerService {

    protected final RabbitTemplate rabbitTemplate;

    protected final RabbitAdmin rabbitAdmin;

    public void sendTransation(TransactionStratum transactionDTO) {
        try {
            String json = new ObjectMapper().writeValueAsString(transactionDTO);
            log.info("Enviando mensagem com dados da conta para a conta : {} ", json);
            rabbitTemplate.convertAndSend(EXCHANGE_TRANSACTION, ROUTING_KEY_TRANSACTION, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
