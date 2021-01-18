package com.example.currency.controller;

import com.example.currency.dto.ResponseDto;
import com.example.currency.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(path = "/currency")
    public ResponseDto getCurrency() {
        return new ResponseDto(currencyService.checkCurrency());
    }

}
