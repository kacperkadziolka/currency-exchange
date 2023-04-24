package com.example.currency_exchange.controller;

import com.example.currency_exchange.data_model.CurrencyConverted;
import com.example.currency_exchange.service.CurrencyService;
import com.example.currency_exchange.utils.CurrencyListEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange/{sourcecurrency}/{destinationcurrency}/{expectedamount}")
    private String getRateExchange(@PathVariable("sourcecurrency") String sourceCurrency,
                                   @PathVariable("destinationcurrency") String destinationCurrency,
                                   @PathVariable("expectedamount") int expectedAmount) {

        // Cast the currency code to upper-case letter
        sourceCurrency = sourceCurrency.toUpperCase();
        destinationCurrency = destinationCurrency.toUpperCase();

        // Validate user input
        if (!CurrencyListEnum.isCurrencyCodeValid(sourceCurrency)) return "Invalid source currency code!";
        if (!CurrencyListEnum.isCurrencyCodeValid(destinationCurrency)) return "Invalid destination currency code!";

        BigDecimal neededAmount = currencyService.calculateConversionAmount(sourceCurrency, destinationCurrency, expectedAmount).setScale(2, RoundingMode.DOWN);
        CurrencyConverted currencyConverted = new CurrencyConverted(sourceCurrency, destinationCurrency, neededAmount, expectedAmount);
        return currencyConverted.toString();
    }
}
