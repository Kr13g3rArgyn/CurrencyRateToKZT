package kz.currency.money_long.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kz.currency.money_long.config.ApiConfiguration;
import kz.currency.money_long.entities.CurrencyEnum;
import kz.currency.money_long.entities.CurrencyRateEntity;
import kz.currency.money_long.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;
    private final ApiConfiguration apiConfiguration;
    private final RestTemplate restTemplate;

    public CurrencyRateService(ApiConfiguration apiConfiguration, RestTemplate restTemplate) {
        this.apiConfiguration = apiConfiguration;
        this.restTemplate = restTemplate;
    }

    public List<CurrencyRateEntity> fetchCurrencyRate() {
        String url = apiConfiguration.getApiUrl();
        String response = restTemplate.getForObject(url, String.class);

        JsonObject root = JsonParser.parseString(Objects.requireNonNull(response)).getAsJsonObject();
        JsonObject currencyHistory = root.getAsJsonObject("data").getAsJsonObject("currencyHistory");

        List<CurrencyRateEntity> currencyRateEntities = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        currencyHistory.entrySet().forEach(entry -> {
            JsonObject dayData = entry.getValue().getAsJsonObject();
            LocalDate date = LocalDate.parse(dayData.get("date").getAsString());

            if (date.isAfter(currentDate.minusDays(10)) && date.isBefore(currentDate.plusDays(1))) {

                JsonObject currencyValue = dayData.getAsJsonObject("legalPersons");
                currencyValue.entrySet().forEach(currencyRate -> {
                    String currencyPair = currencyRate.getKey();
                    if (currencyPair.startsWith("USD/KZT") || currencyPair.startsWith("EUR/KZT") || currencyPair.startsWith("RUB/KZT")) {
                        JsonObject rates = currencyRate.getValue().getAsJsonObject();

                        Double buyRate = rates.get("buy").getAsDouble();
                        Double sellRate = rates.get("sell").getAsDouble();

                        CurrencyEnum currency = CurrencyEnum.valueOf(currencyPair.split("/")[0]);

                        CurrencyRateEntity currencyEntity = new CurrencyRateEntity();
                        currencyEntity.setCurrency(currency);
                        currencyEntity.setBuyRate(buyRate);
                        currencyEntity.setSellRate(sellRate);
                        currencyEntity.setDate(date);

                        currencyRateEntities.add(currencyEntity);
                    }
                });
            }
        });

        return currencyRateEntities;
    }

    public List<CurrencyRateEntity> fetchCurrencyRateFromPaidApi() {
        //TODO fitch: implement fetching data from paid API by the last 10 days
        return currencyRateRepository.findAll();
    }
}

