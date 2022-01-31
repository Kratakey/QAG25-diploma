package helpers;

import java.util.HashMap;
import java.util.Map;

public class CountryConverter {
    public static String getCountryCode(String value) {
        return localeData.get(value);
    }
    
    private static final Map<String, String> localeData = new HashMap<>();
    static {
        localeData.put("Afghanistan", "+93");
        localeData.put("Canada", "+1");
        localeData.put("Finland", "+358");
        localeData.put("France", "+7");
        localeData.put("Germany", "+49");
        localeData.put("Russia", "+7");
    }
}