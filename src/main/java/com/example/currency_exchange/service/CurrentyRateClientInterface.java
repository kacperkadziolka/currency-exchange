package com.example.currency_exchange.service;

import java.math.BigDecimal;

public interface CurrentyRateClientInterface {

    BigDecimal getExchangeRate(String currencyPair);

}
