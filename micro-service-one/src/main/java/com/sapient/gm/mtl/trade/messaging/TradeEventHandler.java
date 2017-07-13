package com.sapient.gm.mtl.trade.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.gm.mtl.trade.model.Trade;
import com.sapient.gm.mtl.trade.repository.TradeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sjai30 on 7/2/2017.
 */

@Component
public class TradeEventHandler {

    @Autowired
    private TradeRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = {"mtl.trade.create"})
    public void createTrade(String trade) throws IOException{
        System.out.print("Trade..." + trade);
        repository.save(objectMapper.readValue(trade, Trade.class));
    }
}
