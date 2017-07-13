package com.sapient.gm.mtl.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.gm.mtl.trade.config.ApplicationConfig;
import com.sapient.gm.mtl.trade.messaging.TradeEventHandler;
import com.sapient.gm.mtl.trade.model.Trade;
import com.sapient.gm.mtl.trade.repository.TradeRepository;
import org.apache.tomcat.jni.Time;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = {ApplicationConfig.class})
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TradeRepository repository;

    @Autowired
    private TradeEventHandler eventHandler;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testOne() throws Exception {
        System.out.println("Sending message...");
        Trade trade = new Trade();
        trade.setTradeId("ABCD");
        trade.setCommodity("JAIN");
        rabbitTemplate.convertAndSend("mtl.trade.exchange", "mtl.trade.create", objectMapper.writeValueAsString(trade));
        List<Trade> trades = repository.findAll();
        System.out.println("Trades from mongo...." + trades);
    }

    @Test
    public void testTwo() {
        List<Trade> trades = repository.findAll();
        System.out.println(trades);
    }
}

