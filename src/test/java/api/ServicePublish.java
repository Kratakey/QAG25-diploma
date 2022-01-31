package api;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.*;

import static helpers.CityIdByName.getCityId;
import static helpers.CountryIdByName.getCountryId;
import static helpers.SubcategoriesById.getSubcategory;
import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpec;

public class ServicePublish extends TestBase {

    public static void changeAccountTypeToProfessional(String accessToken) {

        Map<String, Object> data = new HashMap<>();
        data.put("account_type", "professional");

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .patch(urlBase + ":8000/en/api/accounts/profile/")
                .then()
                .statusCode(200);
    }

    public static int createProfessional(String accessToken, int category, int subcategory, String specialization) {

        Map<String, Object> data = new HashMap<>();
        data.put("subcategory", getSubcategory(category, subcategory));
        data.put("name", specialization);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/professionals/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int createProfessional(String accessToken, int category, int subcategory, String specialization, String level, String about) {

        Map<String, Object> data = new HashMap<>();
        data.put("subcategory", getSubcategory(category, subcategory));
        data.put("description", about);
        data.put("name", specialization);
        data.put("level", level);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/professionals/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int createProfessional(String accessToken, int subcategory, String specialization, String level, String about, String company) {

        Map<String, Object> data = new HashMap<>();
        data.put("subcategory", 1);
        data.put("company", company);
        data.put("description", about);
        data.put("name", specialization);
        data.put("level", level);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/professionals/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    /**
     * Post service prices via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * token, service ID, price, currency, payment methods
     * <br />
     * token, service ID, min price, max price, currency, payment methods
     * </p>
     */
    public static int servicePrices(String accessToken, int serviceId, String servicePrice, String serviceCurrency, String[] paymentMethods) {

        Map<String, Object> data = new HashMap<>();

        data.put("is_price_fixed", true);
        data.put("price_currency", serviceCurrency);
        data.put("price", servicePrice);
        data.put("payment_methods", Arrays.asList(paymentMethods));
        data.put("service", serviceId);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/service-prices/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    /**
     * Post service prices via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * token, service ID, price, currency, payment methods
     * <br />
     * token, service ID, min price, max price, currency, payment methods
     * </p>
     */
    public static int servicePrices(String accessToken, int serviceId, String servicePriceMin, String servicePriceMax, String serviceCurrency, String[] paymentMethods) {

        Map<String, Object> data = new HashMap<>();

        data.put("is_price_fixed", false);
        data.put("start_price_currency", serviceCurrency);
        data.put("end_price_currency", serviceCurrency);
        data.put("start_price", servicePriceMin);
        data.put("end_price", servicePriceMax);
        data.put("payment_methods", Arrays.asList(paymentMethods));
        data.put("service", serviceId);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/service-prices/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    /**
     * Post service schedule via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * token, professional ID, days (default 09:00-18:00)
     * <br />
     * token, professional ID, days, start time, end time
     * </p>
     */
    public static void setSchedule(String accessToken, int professionalId, int days) {

        if (days <= 7 && days >= 0) {
            List<Map<String, Object>> list = new ArrayList<>();

            while (days > 0) {
                Map<String, Object> schedule = new HashMap<>();

                schedule.put("day_of_week", days - 1);
                schedule.put("start_time", "09:00");
                schedule.put("end_time", "18:00");
                schedule.put("is_enabled", true);
                schedule.put("professional", professionalId);

                list.add(schedule);
                days--;
            }

            Map[] schedules = list.toArray(new HashMap[list.size()]);

            given()
                    .spec(requestSpec)
                    .header("Authorization", "Bearer " + accessToken)
                    .body(Arrays.asList(schedules))
                    .when()
                    .post(urlBase + ":8000/en/api/accounts/professional-schedule/set/")
                    .then()
                    .statusCode(201);
        } else {
            System.out.println("There are only 7 days in the week, can not accept days: " + days);
            throw new IllegalArgumentException();
        }
    }

    /**
     * Post service schedule via API
     * <br />
     * <br />
     * <p>
     * Formats:
     * <br />
     * token, professional ID, days (default 09:00-18:00)
     * <br />
     * token, professional ID, days, start time, end time
     * </p>
     */
    public static void setSchedule(String accessToken, int professionalId, int days, String startTime, String endTime) {

        if (!startTime.contains(":")) {
            String hours = String.valueOf(startTime).substring(0, 2);
            String minutes = String.valueOf(startTime).substring(2);
            startTime = hours + ":" + minutes;
        }

        if (!endTime.contains(":")) {
            String hours = String.valueOf(endTime).substring(0, 2);
            String minutes = String.valueOf(endTime).substring(2);
            endTime = hours + ":" + minutes;
        }

        if (days <= 7 && days >= 0) {
            List<Map<String, Object>> list = new ArrayList<>();

            int i = 0;
            while (i < days) {
                Map<String, Object> schedule = new HashMap<>();

                schedule.put("day_of_week", i);
                schedule.put("start_time", startTime);
                schedule.put("end_time", endTime);
                schedule.put("is_enabled", true);
                schedule.put("professional", professionalId);

                list.add(schedule);
                i++;
            }

            Map[] schedules = list.toArray(new HashMap[list.size()]);

            given()
                    .spec(requestSpec)
                    .header("Authorization", "Bearer " + accessToken)
                    .body(Arrays.asList(schedules))
                    .when()
                    .post(urlBase + ":8000/en/api/accounts/professional-schedule/set/")
                    .then()
                    .statusCode(201);
        } else {
            System.out.println("There are only 7 days in the week, can not accept " + days + " days.");
            throw new IllegalArgumentException();
        }
    }

    public static int professionalLocations(String accessToken, int professionalId, String country, String city, String address, int units) {

        Map<String, Object> data = new HashMap<>();
        data.put("country", getCountryId(country));
        data.put("city", getCityId(city));
        data.put("address", address);
        data.put("professional", professionalId);
        data.put("units", units);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/professional-locations/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int professionalLocations(String accessToken, int professionalId, String country, String city, String address) {

        Map<String, Object> data = new HashMap<>();
        data.put("country", getCountryId(country));
        data.put("city", getCityId(city));
        data.put("address", address);
        data.put("professional", professionalId);
        data.put("units", 0);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/professional-locations/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static void serviceLocations(String accessToken, int serviceId, int serviceLocationId, int serviceMaxDistance) {

        Map<String, Object> data = new HashMap<>();
        data.put("service", serviceId);
        data.put("max_distance", serviceMaxDistance);
        data.put("location", serviceLocationId);

        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/service-locations/")
                .then()
                .statusCode(201);
    }

    public static int serviceLocations(String accessToken, int serviceId, int serviceLocationId) {

        Map<String, Object> data = new HashMap<>();
        data.put("service", serviceId);
        data.put("max_distance", 0);
        data.put("location", serviceLocationId);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/service-locations/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int servicePublish(String accessToken, int professionalId, String serviceName, String serviceDescription, String serviceDuration, int serviceLocation, Boolean instantBooking) {

        int duration = Integer.parseInt(serviceDuration);

        String serviceType = null;
        switch (serviceLocation) {
            case 0:
                serviceType = "online";
                break;
            case 1:
                serviceType = "client";
                break;
            case 2:
                serviceType = "professional";
                break;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("name", serviceName);
        data.put("description", serviceDescription);
        data.put("duration", duration);
        data.put("service_type", serviceType);
        data.put("is_base_schedule", true);
        data.put("is_auto_order_confirmation", instantBooking);
        data.put("is_enabled", true);
        data.put("professional", professionalId);

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/services/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }
}