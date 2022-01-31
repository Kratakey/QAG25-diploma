package helpers;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CountryIdByName extends TestBase {

    public static int getCountryId(String country) {
        Response response = countryIdByName(country);
        int i = 0;
        String countryNames = response.path("results.name[" + i + "]");
        while (!countryNames.equals(null)) {
            countryNames = response.path("results.name[" + i + "]");
            if (countryNames.equals(country)) {
                break;
            }
            i++;
        }
        return response.path("results.id[" + i + "]");
    }

    public static Response countryIdByName(String country) {

        return given()
                .filter(new AllureRestAssured())
                .accept("application/json")
                .header("Referer", urlBase + ":8000/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBase + ":8000/en/api/location/countries/?search=" + country)
                .then()
                .statusCode(200)
                .extract().response();
    }
/*
    public static String getCountryId2(String country) {
        return countryData.get(country);
    }

    private static final Map<String, String> countryData = new HashMap<>();
    static {
        countryData.put("Afghanistan", "1149361");
        countryData.put("Canada", "6251999");
        countryData.put("Finland", "660013");
        countryData.put("France", "3017382");
        countryData.put("Germany", "2921044");
        countryData.put("Russia", "2017370");
    }
*/
}