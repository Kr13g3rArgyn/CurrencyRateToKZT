package kz.currency.money_long.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

    @Getter
    @Configuration
    public class ApiConfiguration {
        @Value("${api.v1.url}")
        private String apiUrl;

        @Value("${api.v2.url}")
        private String apiUrlFreakApi;

        @Value("${api.v2.apikey}")
        private String apiV2ApiKey;

        @Value("${api.v2.start-date}")
        private String startDate;

        @Value("${api.v2.end-date}")
        private String endDate;

        @Value("${api.v2.base_currency}")
        private String baseCurrency;

        @Value("${api.v2.currencies}")
        private String currencies;
    }
