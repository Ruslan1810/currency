package com.example.currency.service;

import com.example.currency.client.CurrencyClient;
import com.example.currency.client.GiphyClient;
import com.example.currency.dto.CurrencyDto;
import com.example.currency.dto.GiphyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final String currencyToken;
    private final CurrencyClient currencyClient;
    private final GiphyClient giphyClient;
    private final String giphyToken;

    public CurrencyServiceImpl(@Value("${currency.token}") String currencyToken, @Value("${giphy.token}") String giphyToken,
                               CurrencyClient currencyClient, GiphyClient giphyClient) {
        this.currencyToken = currencyToken;
        this.currencyClient = currencyClient;
        this.giphyToken = giphyToken;
        this.giphyClient = giphyClient;
    }

    @Override
    public String checkCurrency() {
        //Получение вчерашней даты
        LocalDate dateYesterday = LocalDate.now().minusDays(1);
        String formattedDateYesterday = dateYesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Получение сегодняшней даты
        LocalDate dateToday = LocalDate.now();
        String formattedDateToday = dateToday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Ответ на запрос о вчерашнем курсе
        ResponseEntity<CurrencyDto> historyResponse = currencyClient.getCurrencyHistory(formattedDateYesterday, currencyToken);

        //Если в статусе ответа ошибка, то предупреждение
        if (!(historyResponse == null) && historyResponse.getStatusCode().isError()) {
            throw new RuntimeException("Ошибка при получении курса валют");
        }

        //Ответ на запрос о сегодняшнем курсе
        ResponseEntity<CurrencyDto> currencyResponse = currencyClient.getLatestCurrency(formattedDateToday, currencyToken);

        //Если в статусе ответа ошибка, то предупреждение
        if (!(currencyResponse == null) && currencyResponse.getStatusCode().isError()) {
            throw new RuntimeException("Ошибка при получении текущей валюты");
        }

        //Курс рубля вчерашний
        Double yesterdayRate = historyResponse.getBody().getRates().get("RUB");

        //Курс рубля сегодняшний
        Double todayRate = currencyResponse.getBody().getRates().get("RUB");

        //Ответ, на запрос гифки
        ResponseEntity<GiphyDto> giphyResponse;

        //Если курс больше, то отправляется ссылка на гифку богатства, иначе гифка бедности
        if (yesterdayRate > todayRate) {
            giphyResponse = giphyClient.getGiphyBroke(giphyToken);
        } else {
            giphyResponse = giphyClient.getGiphyRich(giphyToken);
        }

        return giphyResponse.getBody().getData().getUrl();

    }
}
