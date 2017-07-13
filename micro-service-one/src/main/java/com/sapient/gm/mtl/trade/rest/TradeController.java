package com.sapient.gm.mtl.trade.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.gm.mtl.trade.model.Trade;
import com.sapient.gm.mtl.trade.repository.TradeRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by sjai30 on 7/2/2017.
 */

@RestController
public class TradeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TradeRepository repository;

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping(value="/create", method = RequestMethod.POST)
    public void createTrade(@RequestBody Trade trade) throws IOException{
        String tradeJson = objectMapper.writeValueAsString(trade);
        rabbitTemplate.convertAndSend("mtl.trade.exchange", "mtl.trade.create", tradeJson);
    }

    @RequestMapping(value="/getAllTrades")
    public List<Trade> getAllTrades() {
        return repository.findAll();
    }

    @RequestMapping(value="/getTradeByCounterparty")
    public List<Trade> getTradeByCounterparty(@RequestParam(value = "counterparty", defaultValue = "BARCLO") String counterparty) {
        return repository.findByCounterparty(counterparty);
    }
}
