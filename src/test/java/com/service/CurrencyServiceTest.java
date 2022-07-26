package com.service;

import currencyservice.api.service.CurrencyService;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {

    CurrencyService currencyService = new CurrencyService();

    @Test
    public void urlTest() {
        assertEquals("https://api.exchangerate.host/convert?from=EUR&to=USD", currencyService.createURL("EUR", "USD"));
    }

    @Disabled("The exchangerate is changing all the time")
    @Test
    public void jsonTest() throws JSONException, IOException {
        assertEquals(1.003603, currencyService.getExchangeRate("EUR_USD"));
    }
}