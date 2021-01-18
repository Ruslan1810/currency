package com.example.currency.service;

import com.example.currency.client.CurrencyClient;
import com.example.currency.client.GiphyClient;
import com.example.currency.dto.CurrencyDto;
import com.example.currency.dto.GiphyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
public class CurrencyServiceTest {

    @MockBean
    private CurrencyClient currencyClient;
    @MockBean
    private GiphyClient giphyClient;

    @Test
    public void checkServiceTest(){
        //Тестовые данные для получения вчерашнего курса
        Map<String, Double> ratesYesterday =  new HashMap<>();
        ratesYesterday.put("RUB", 23.0);
        CurrencyDto currencyYesterdayDto  = new CurrencyDto(1L, "USD", ratesYesterday);

        //Тестовые данные для получения сегодняшнего курса
        Map<String, Double> ratesToday = new HashMap<>();
        ratesToday.put("RUB", 58.0);
        CurrencyDto currencyTodayDto = new CurrencyDto(3L, "USD", ratesToday);

        //Тестовые данные для получения гифки богатства
        GiphyDto.GiphyData dataGiphyRich = new GiphyDto.GiphyData("https//ytryt");
        GiphyDto giphyDtoGiphyRich = new GiphyDto(dataGiphyRich);

        //Тестовые данные для получения гифки бедности
        GiphyDto.GiphyData dataGiphyBroke = new GiphyDto.GiphyData("https//jjjj");
        GiphyDto giphyDtoGiphyBroke  = new GiphyDto(dataGiphyBroke);

        Mockito.when(currencyClient.getCurrencyHistory(Mockito.anyString(), Mockito.anyString())).thenReturn(ResponseEntity.of(Optional.of(currencyYesterdayDto)));
        Mockito.when(currencyClient.getLatestCurrency(Mockito.anyString(), Mockito.anyString())).thenReturn(ResponseEntity.of(Optional.of(currencyTodayDto)));
        Mockito.when(giphyClient.getGiphyRich(Mockito.anyString())).thenReturn(ResponseEntity.of(Optional.of(giphyDtoGiphyRich)));
        Mockito.when(giphyClient.getGiphyBroke(Mockito.anyString())).thenReturn(ResponseEntity.of(Optional.of(giphyDtoGiphyBroke)));

        CurrencyService currencyService = new CurrencyServiceImpl("", "", currencyClient, giphyClient);
        String gifUrl = currencyService.checkCurrency();

        Double yesterdayRate = ratesYesterday.get("RUB");
        Double todayRate = ratesToday.get("RUB");

        Assertions.assertEquals(23, yesterdayRate, "Ошибка - курс рубля за вчерашний день не равен ожидаемому");
        Assertions.assertEquals(58, todayRate, "Ошибка - курс рубля на сегодня не равен ожидаемому");
        Assertions.assertEquals("https://giphy.com/gifs/andersonpaak-money-pool-lptjRBxFKCJmFoibP3", gifUrl,
                "Ошибка - не корректная ссылка");
    }
}
