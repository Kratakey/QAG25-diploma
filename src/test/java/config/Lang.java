package config;

public enum Lang {
    ENGLISH("ENG"), RUSSIAN("RUS"), GERMAN("DEU"), FRENCH("FRE"), HISPANIC("ESP");

    private final String lang;

    Lang(String lang) {
        this.lang = lang;
    }

    public String getLangText() {
        return lang;
    }
}