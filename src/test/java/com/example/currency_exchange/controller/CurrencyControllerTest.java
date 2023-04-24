package com.example.currency_exchange.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testValidCurrencyCodes() {
        // Given
        String url = "/exchange/USD/GBP/100";

        // When
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInvalidSourceCurrency() {
        // Given
        String url = "/exchange/INVALID/GBP/100";

        // When
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invalid source currency code!", response.getBody());
    }

    @Test
    public void testInvalidDestinationCurrency() {
        // Given
        String url = "/exchange/USD/INVALID/100";

        // When
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invalid destination currency code!", response.getBody());
    }

}