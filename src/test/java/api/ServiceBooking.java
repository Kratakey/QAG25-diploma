package api;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static helpers.CityIdByName.getCityId;
import static helpers.CountryIdByName.getCountryId;
import static helpers.DateTimeFormatter.getDateTime;
import static helpers.SubcategoriesById.getSubcategory;
import static io.restassured.RestAssured.given;
import static specifications.Specifications.requestSpec;

public class ServiceBooking extends TestBase {

    public static int booking(String accessToken, int serviceId, int locationsId, int serviceLocation, String dateTime) {

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", dateTime);
        data.put("is_another_person", false);
        data.put("first_name", "");
        data.put("last_name", "");
        data.put("email", "");
        data.put("phone", "");
        data.put("note", "");
        data.put("source", "manual");

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int booking(String accessToken, int serviceId, int locationsId, int serviceLocation) {

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", getDateTime());
        data.put("is_another_person", false);
        data.put("first_name", "");
        data.put("last_name", "");
        data.put("email", "");
        data.put("phone", "");
        data.put("note", "");
        data.put("source", "manual");

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int booking(String accessToken, int serviceId, int locationsId, int serviceLocation, String dateTime,
                                String firstName, String lastName, String email, String phone, String note) {

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", dateTime);
        data.put("is_another_person", true);
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email", email);
        data.put("phone", phone);
        data.put("note", note);
        data.put("source", "online");

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }

    public static int booking(String accessToken, int serviceId, int locationsId, int serviceLocation,
                                String firstName, String lastName, String email, String phone, String note) {

        Map<String, Object> data = new HashMap<>();

        data.put("service", serviceId);
        if (serviceLocation > 0) {
            if (serviceLocation == 1) {
                data.put("client_location", locationsId);
            } else if (serviceLocation == 2) {
                data.put("service_location", locationsId);
            } else {
                System.out.println("Service location is not a valid choice.");
                throw new IllegalArgumentException();
            }
        }
        data.put("start_datetime", getDateTime());
        data.put("is_another_person", true);
        data.put("first_name", firstName);
        data.put("last_name", lastName);
        data.put("email", email);
        data.put("phone", phone);
        data.put("note", note);
        data.put("source", "online");

        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + accessToken)
                .body(data)
                .when()
                .post(urlBase + ":8000/en/api/accounts/orders/sent/")
                .then()
                .statusCode(201)
                .extract().response().path("id");
    }
}