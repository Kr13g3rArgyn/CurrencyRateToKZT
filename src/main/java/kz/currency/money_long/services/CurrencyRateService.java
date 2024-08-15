package kz.currency.money_long.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kz.currency.money_long.config.ApiConfiguration;
import kz.currency.money_long.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;
    private final ApiConfiguration apiConfiguration;

    public CurrencyRateService (ApiConfiguration apiConfiguration){
        this.apiConfiguration = apiConfiguration;
    }
    private void fetchCurrencyRate() throws IOException {
        String apiUrl = apiConfiguration.getApiUrl();
        URL url = new URL(apiUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try(var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(reader).getAsJsonObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
