package diploma.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static diploma.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;

public class DemowebshopSpecs {
    public static RequestSpecification demowebshopRequestSpec = with()
            .baseUri("http://demowebshop.tricentis.com")
            .basePath("/login")
            .log().all()
            .filter(customLogFilter().withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification demowebshopResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(302)
            .build();
}