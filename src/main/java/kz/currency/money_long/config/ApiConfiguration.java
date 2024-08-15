package kz.currency.money_long.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

    @Getter
    @Configuration
    public class ApiConfiguration {
        @Value("${api.url}")
        private String apiUrl;
    }
