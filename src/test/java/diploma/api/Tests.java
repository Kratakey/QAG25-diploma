package diploma.api;

import diploma.lombok.LombokUserDataReqres;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static diploma.specifications.DemoqaSpecs.*;
import static diploma.specifications.DemowebshopSpecs.demowebshopRequestSpec;
import static diploma.specifications.DemowebshopSpecs.demowebshopResponseSpec;
import static diploma.specifications.ReqresInSpecs.reqresRequestSpec;
import static diploma.specifications.ReqresInSpecs.reqresResponseSpec;

public class Tests {

    String
            email = "email@test.gg",
            password = "1234Pass#";

    @Test
    void getUserWithLombok() {
        step("Get User", () -> {
            LombokUserDataReqres data = given()
                    .spec(reqresRequestSpec)
                    .when()
                    .get("/2")
                    .then()
                    .spec(reqresResponseSpec)
                    .log().body()
                    .extract().as(LombokUserDataReqres.class);
            assertEquals("Weaver", data.getUser().getLastName());
        });
    }

    @Test
    void getUsersWithGroovyCheck() {
        step("Get User", () -> {
            given()
                    .spec(reqresRequestSpec)
                    .when()
                    .get("?page=2")
                    .then()
                    .spec(reqresResponseSpec)
                    .log().body()
                    .body("data.findAll{it.first_name =~/.*say/}.last_name.flatten()", hasItem("Ferguson"));
        });
    }

    @Test
    void simpleLogin() {
        step("Login by API, Demowebshop", () -> {
            given()
                    .spec(demowebshopRequestSpec)
                    .formParam("Email", email)
                    .formParam("Password", password)
                    .when()
                    .post()
                    .then()
                    .spec(demowebshopResponseSpec)
                    .log().cookies();
        });
    }

    @Test
    void noLogsTest() {
        given()
                .spec(requestSpecBookStore)
                .get("/Books")
                .then()
                .spec(responseSpecBookStore);
    }

    @Test
    void withAllLogsTest() {
        given()
                .spec(requestSpecBookStore)
                .get("/Books")
                .then()
                .log().all()
                .spec(responseSpecBookStore);
    }

    @Test
    void withSomeLogsTest() {
        given()
                .spec(requestSpecBookStore)
                .get("/Books")
                .then()
                .log().body()
                .spec(responseSpecBookStore);
    }


    @Test
    void authorizeWithListenerTest() {
        Map<String, String> data = new HashMap<>();
        data.put("userName", email);
        data.put("password", password);

        given()
                .spec(requestSpecAccount)
                .body(data)
                .post("/GenerateToken")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("result", is("User authorized successfully."));
    }

    @Test
    void authorizeWithTemplatesTest() {
        Map<String, String> data = new HashMap<>();
        data.put("userName", email);
        data.put("password", password);

        step("Generate token", () ->
                given()
                        .spec(requestSpecAccount)
                        .body(data)
                        .post("/GenerateToken")
                        .then()
                        .spec(responseSpec)
                        .log().body()
                        .body("result", is("User authorized successfully."))
        );
        step("Any UI action");
    }

    @Test
    void loginWithShema() {
        Map<String, String> data = new HashMap<>();
        data.put("userName", email);
        data.put("password", password);

        given()
                .spec(requestSpecAccount)
                .body(data)
                .post("/Authorized")
                .then()
                .spec(responseSpecAuth)
                .log().body()
                .body(matchesJsonSchemaInClasspath("shemas/loginShema.json"));
    }
}