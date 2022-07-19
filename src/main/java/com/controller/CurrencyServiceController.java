package com.controller;

import com.service.CurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class CurrencyServiceController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping(path = "/rate/{currencies}", produces = "application/json")
    public ResponseEntity<Double> getExchangeRate(@PathVariable(value = "currencies") String currencies)
            throws JSONException, IOException {
        Double exchangeRate = currencyService.getExchangeRate(currencies);

        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }
    
}
