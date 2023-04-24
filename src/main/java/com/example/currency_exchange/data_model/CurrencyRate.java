package com.example.currency_exchange.data_model;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRate {

    @JsonProperty("rates")
    private Map<String, List<String>> rates;

    @JsonProperty("rates")
    public Map<String, List<String>> getRates() {
        return rates;
    }

    @JsonProperty("rates")
    public void setRates(Map<String, List<String>> currencyRates) {
        this.rates = currencyRates;
    }

    public List<String> getRatesForCurrencyPair(String currencyPair) {
        return rates.get(currencyPair);
    }

}