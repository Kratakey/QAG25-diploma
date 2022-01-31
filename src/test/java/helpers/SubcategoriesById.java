package helpers;

import config.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SubcategoriesById extends TestBase {

    public static int getSubcategory(int category, int subcategory) {
        Response response = subcategories();
        category++;
        subcategory++;
        if (category > 3) {
            category++;
            if (category == 10) {
                category = 9;
            } else if (category == 9) {
                category = 10;
            }
        }
        int i = 0;
        int categoryCheck;
        int subcategoryCounter = 0;
        while (i < subcategory) {
            categoryCheck = response.path("results.category[" + subcategoryCounter + "]");
            if (categoryCheck == category) {
                i++;
            }
            subcategoryCounter++;
        }
        if (subcategoryCounter > 26) {
            subcategoryCounter = subcategoryCounter + 3;
        }
        return subcategoryCounter;
    }

    public static Response subcategories() {

        return given()
                .filter(new AllureRestAssured())
                .accept("application/json")
                .header("Referer", urlBase + ":8000/swagger/")
                .header("x-timezone", xTimeZone)
                .when()
                .get(urlBase + ":8000/en/api/professionals/subcategories/?page_size=900")
                .then()
                .statusCode(200)
                .extract().response();
    }
}