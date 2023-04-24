package com.example.currency_exchange.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyService implements CurrencyServiceInterface {

    private final CurrencyRateClient currencyRateClient;

    public CurrencyService(CurrencyRateClient currencyRateClient) {
        this.currencyRateClient = currencyRateClient;
    }

    public BigDecimal calculateConversionAmount(String sourceCurrency, String destinationCurrency, int expectedAmount) {
        String currencyPair = destinationCurrency + sourceCurrency;
        BigDecimal currencyRate = currencyRateClient.getExchangeRate(currencyPair);
        return currencyRate.multiply(BigDecimal.valueOf(expectedAmount));
    }

}
