package config;

public enum Currencies {
    CAD("$"), EUR("€"), RUB("₽"), USD("$");

    private final String currencies;

    Currencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCurrencyText() {
        return currencies;
    }
}