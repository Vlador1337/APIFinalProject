package api_steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static utils.Configuration.getConfigurationValue;
import static utils.Const.*;

public class Spec {

    @Before
    public void setFilters() {
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new AllureRestAssured());
        }
    }

    @After
    public void cleanSpec(){
        RestAssured.requestSpecification = null;
    }


    public static RequestSpecification rickAndMortyRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getConfigurationValue(RICK_AND_MORTY_BASE_URL_KEY))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification reqresRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getConfigurationValue(REQRES_BASE_URL_KEY))
                .setContentType(ContentType.JSON)
                .setBasePath(getConfigurationValue(REQRES_BASE_PATH_KEY))
                .build();
    }

    public static void setSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static ResponseSpecification responseSpecification() {
        return responseSpecification(200);
    }

    public static ResponseSpecification responseSpecification(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}