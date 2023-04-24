package com.example.currency_exchange.service;

import com.example.currency_exchange.data_model.CurrencyRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyRateClient implements CurrentyRateClientInterface {

    private final RestTemplate restTemplate;

    private final String baseUrl = "https://devapi.currencycloud.com/v2/rates/find?currency_pair=";

    @Value("${auth.token}")
    private String authToken;

    public CurrencyRateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal getExchangeRate(String currencyPair) {
        String url = baseUrl + currencyPair;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token", authToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        ResponseEntity<CurrencyRate> response = restTemplate.exchange(url, HttpMethod.GET, entity, CurrencyRate.class);
        String exchangeRate = response.getBody().getRatesForCurrencyPair(currencyPair).get(0);
        return new BigDecimal(exchangeRate);
    }
}
