package com.sapient.gm.mtl.trade.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by sjai30 on 6/1/2017.
 */


@Data
@NoArgsConstructor

public class Trade {


    @Id
    String tradeId;

    LocalDate tradeDate;

    BigDecimal quantity;

    BigDecimal price;

    String location;

    String commodity;

    String counterparty;


}
