package com.example.currency_exchange.utils;

public enum CurrencyListEnum {

    USD, EUR, GBP, PLN, JPY, CHF, AUD, CAD, HKD, NZD;

    public static boolean isCurrencyCodeValid(String currencyCode) {
        for (CurrencyListEnum currencyListEnum : CurrencyListEnum.values()) {
            if (currencyListEnum.name().equalsIgnoreCase(currencyCode)) return true;
        }
        return false;
    }
}
