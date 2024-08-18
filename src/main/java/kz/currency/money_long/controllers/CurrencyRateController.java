package kz.currency.money_long.controllers;

import kz.currency.money_long.entities.CurrencyRateEntity;
import kz.currency.money_long.services.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currency-rate")
public class CurrencyRateController {
    @Autowired
    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/halyk")
    public List<CurrencyRateEntity> getLatestCurrencyRate(){
        return currencyRateService.fetchCurrencyRate();
    }

    @GetMapping("/paid-api") //I didn't pay
    public List<CurrencyRateEntity> getLatestCurrencyRateFromPaidApi(){
        return currencyRateService.fetchCurrencyRateForPeriod();
    }
}
