package diploma.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static diploma.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

public class DemoqaSpecs {
    public static RequestSpecification requestSpecAccount = with()
            .log().all()
            .filter(customLogFilter().withCustomTemplates())
            .baseUri("https://demoqa.com")
            .basePath("/Account/v1")
            .contentType("application/json")
            .when()
            .log().uri()
            .log().body();

    public static RequestSpecification requestSpecBookStore = with()
            .log().all()
            .filter(customLogFilter().withCustomTemplates())
            .baseUri("https://demoqa.com")
            .basePath("/BookStore/v1")
            .contentType("application/json");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()

            .expectStatusCode(200)
            .expectBody(containsString("success"))
            .build();

    public static ResponseSpecification responseSpecBookStore = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("books", hasSize(greaterThan(0)))
            .build();

    public static ResponseSpecification responseSpecAuth = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(containsString("true"))
            .build();
}