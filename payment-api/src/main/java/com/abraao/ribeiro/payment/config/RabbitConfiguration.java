package com.abraao.ribeiro.payment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

import static com.abraao.ribeiro.payment.constant.RabbitConstant.*;

@Configuration
@ConditionalOnClass(EnableRabbit.class)
public class RabbitConfiguration {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Autowired
    protected RabbitAdmin rabbitAdmin;


    @PostConstruct
    protected void init() {
        rabbitTemplate.setChannelTransacted(true);
        rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE_TRANSACTION, true, false));
        rabbitAdmin.declareQueue(new Queue(QUEEU_TRANSACTION, true, false, false, null));
        rabbitAdmin.declareBinding(new Binding(QUEEU_TRANSACTION, Binding.DestinationType.QUEUE, EXCHANGE_TRANSACTION, ROUTING_KEY_TRANSACTION, null));
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
