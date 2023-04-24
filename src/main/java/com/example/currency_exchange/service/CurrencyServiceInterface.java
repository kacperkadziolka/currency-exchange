package com.example.currency_exchange.service;

import java.math.BigDecimal;

public interface CurrencyServiceInterface {

    BigDecimal calculateConversionAmount(String sourceCurrency, String destinationCurrency, int expectedAmount);

}
