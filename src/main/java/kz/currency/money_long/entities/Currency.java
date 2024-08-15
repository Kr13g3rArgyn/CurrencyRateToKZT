package kz.currency.money_long.entities;

import lombok.Getter;

@Getter
public enum Currency {
    EUR("EURO", "€"),
    USD("US Dollar", "$"),
    RUB("Russian Ruble", "₽");
    private final String description;
    private final String symbol;
    Currency(String description, String symbol) {
        this.description = description;
        this.symbol = symbol;
    }

}
