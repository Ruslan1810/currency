package com.example.currency.dto;

import java.util.Map;

public class CurrencyDto {
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;

    public CurrencyDto(Long timestamp, String base, Map<String, Double> rates) {
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public CurrencyDto() {
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
