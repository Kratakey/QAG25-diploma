package helpers;

import com.github.javafaker.Faker;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.fail;

public class SubcategoryGenerator {
    public static Integer getRandomSubcategoryFromCategoryValue(Integer value) {
        Faker generate = new Faker(new Locale("en-US"));
        switch (value) {
            case 0:
            case 1:
            case 3:
            case 5:
                value = generate.number().numberBetween(0, 8);
                break;
            case 2:
            case 6:
            case 8:
                value = generate.number().numberBetween(0, 7);
                break;
            case 4:
                value = generate.number().numberBetween(0, 5);
                break;
            case 7:
                value = generate.number().numberBetween(0, 1);
                break;
            default:
                fail();
                break;
        }
        return value;
    }
}