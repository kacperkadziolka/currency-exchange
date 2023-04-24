package com.example.currency_exchange.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRateClient currencyRateClient;

    @InjectMocks
    private CurrencyService underTestCurrencyService;

    @Test
    void itShouldCalculateConversionAmount() {
        // Given
        String sourceCurrency = "EUR";
        String destinationCurrency = "PLN";
        int expectedAmount = 1000;

        String currencyPair = destinationCurrency + sourceCurrency;
        BigDecimal currencyRate = BigDecimal.valueOf(0.238073);
        BigDecimal expectedConversionAmount = BigDecimal.valueOf(238.07);

        // When
        when(currencyRateClient.getExchangeRate(currencyPair)).thenReturn(currencyRate);
        BigDecimal actualConversionAmount = underTestCurrencyService.calculateConversionAmount(
                sourceCurrency, destinationCurrency, expectedAmount)
                .setScale(2, RoundingMode.DOWN);

        // Then
        assertEquals(expectedConversionAmount, actualConversionAmount);
    }

    @Test
    void itShouldNotCalculateConversionAmount() {
        // Given
        String sourceCurrency = "EUP";
        String destinationCurrency = "PLN";
        int expectedAmount = 1000;

        String currencyPair = destinationCurrency + sourceCurrency;
        BigDecimal currencyRate = BigDecimal.valueOf(0.238073);
        BigDecimal expectedConversionAmount = BigDecimal.valueOf(239.07);

        // When
        when(currencyRateClient.getExchangeRate(currencyPair)).thenReturn(currencyRate);
        BigDecimal actualConversionAmount = underTestCurrencyService.calculateConversionAmount(
                        sourceCurrency, destinationCurrency, expectedAmount)
                .setScale(2, RoundingMode.DOWN);

        // Then
        assertNotEquals(expectedConversionAmount, actualConversionAmount);
    }
}