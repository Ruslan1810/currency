package com.example.currency.client;

import com.example.currency.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currencyClient", url = "${currency.url}")
public interface CurrencyClient {

    //Запрос вчерашнего курса
    @RequestMapping(method = RequestMethod.GET, value = "${currency.history.url}")
    ResponseEntity<CurrencyDto> getCurrencyHistory(@PathVariable String date, @RequestParam("app_id") String appId);

    //Запрос сегодняшнего курса
    @RequestMapping(method = RequestMethod.GET, value = "${currency.latest.url}")
    ResponseEntity<CurrencyDto> getLatestCurrency(@PathVariable String date, @RequestParam("app_id") String appId);

}
