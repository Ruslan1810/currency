package com.example.currency.client;

import com.example.currency.dto.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphyClient", url = "${giphy.url}")
public interface GiphyClient {

    //Запрос гифки богатства
    @RequestMapping(method = RequestMethod.GET, value = "${giphy.rich.url}")
    ResponseEntity<GiphyDto> getGiphyRich(@RequestParam("api_key") String apiKey);

    //Запрос гифки бедности
    @RequestMapping(method = RequestMethod.GET, value = "${giphy.broke.url}")
    ResponseEntity<GiphyDto> getGiphyBroke (@RequestParam("api_key") String apiKey);
}
