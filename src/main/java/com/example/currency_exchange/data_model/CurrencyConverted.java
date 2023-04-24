package com.example.currency_exchange.data_model;

import java.math.BigDecimal;

public class CurrencyConverted {

    private String sourceCurrency;

    private String destinationCurrency;

    private BigDecimal requiredAmount;

    private int expectedAmount;

    public CurrencyConverted(String sourceCurrency, String destinationCurrency, BigDecimal requiredAmount, int expectedAmount) {
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.requiredAmount = requiredAmount;
        this.expectedAmount = expectedAmount;
    }

    @Override
    public String toString() {
        return "To buy " + expectedAmount + " " + destinationCurrency + " you will need " + requiredAmount + " " + sourceCurrency;
    }
}
