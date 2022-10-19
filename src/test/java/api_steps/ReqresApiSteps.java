package api_steps;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static api_steps.Spec.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static utils.Const.*;

public class ReqresApiSteps {
    private JSONObject newUserBody;
    private JSONObject requestBody;

    @Если("создать нового пользователя с именем - {string} и местом работы - {string} и отправить Post запрос")
    public void createUser(String nameValue, String jobValue) throws IOException {
        setSpecification(reqresRequestSpec(), responseSpecification(201));
        requestBody = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/reqres.json"))));
        requestBody.put(NAME, nameValue);
        requestBody.put(JOB, jobValue);

        Response newUser = given()
                .body(requestBody.toString())
                .post()
                .then()
                .extract().response();

        newUserBody = new JSONObject(newUser.getBody().asString());
    }

    @Тогда("придет ответ с кодом \"201\" и тело с информацией")
    public void checkData() {
        assertEquals(newUserBody.getString(NAME),
                requestBody.getString(NAME), "Имена не совпадают");
        assertEquals(newUserBody.getString(JOB),
                requestBody.getString(JOB), "Работа не совпадает");
        assertNotNull(newUserBody.getString(ID), "ID пустое");
        assertNotNull(newUserBody.getString(CREATED_AT), "Дата создания пустая");
    }
}