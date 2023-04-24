package com.example.currency_exchange.service;

import com.example.currency_exchange.data_model.CurrencyRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyRateClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${auth.token}")
    private String authToken;

    @Test
    void itShouldReturnSuccessfulResponse() {
        // Given
        String url = "https://devapi.currencycloud.com/v2/rates/find?currency_pair=USDGBP";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token", authToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        // When
        ResponseEntity<CurrencyRate> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, CurrencyRate.class);

        // Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void itShouldReturnUnauthorizedError() {
        // Given
        String url = "https://devapi.currencycloud.com/v2/rates/find?currency_pair=USDGBP";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token", "wrong-token");
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);

        // When
        ResponseEntity<CurrencyRate> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, CurrencyRate.class);

        // Then
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}