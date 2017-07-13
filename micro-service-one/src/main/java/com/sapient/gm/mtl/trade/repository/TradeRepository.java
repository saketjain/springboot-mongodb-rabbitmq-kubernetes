package com.sapient.gm.mtl.trade.repository;

import com.sapient.gm.mtl.trade.model.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TradeRepository extends MongoRepository<Trade, String> {

    public List<Trade> findByCounterparty(String counterparty);

}