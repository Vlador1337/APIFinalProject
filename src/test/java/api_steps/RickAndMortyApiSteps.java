package api_steps;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import models.Result;

import java.util.ArrayList;
import java.util.List;

import static api_steps.Spec.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Configuration.getConfigurationValue;
import static utils.Const.*;

public class RickAndMortyApiSteps {

    private List<Result> allMorty;
    private Result firstMorty;
    private Result lastCharacter;
    private int lastEpisodeId;
    private int lastCharacterId;

    @Если("получить информацию о всех {string}")
    public void getAllMorty(String keyValue) {
        setSpecification(rickAndMortyRequestSpec(), responseSpecification());
        allMorty = given()
                .param(NAME, keyValue)
                .get(getConfigurationValue(ENDPOINT_CHARACTER_KEY))
                .then()
                .extract().body().jsonPath().getList(RESULTS, Result.class);
    }

    @Когда("отправить Get запрос на endpoint characters с ID первого Морти")
    public void getFirstMortyInfo() {
        firstMorty = given()
                .get(getConfigurationValue(ENDPOINT_CHARACTER_KEY) + allMorty.get(0).getId())
                .then()
                .extract().body().as(Result.class);
    }

    @Когда("получить ID последней серии")
    public void getLastEpisodeId() {
        setSpecification(rickAndMortyRequestSpec(), responseSpecification());
        lastEpisodeId = given()
                .get(getConfigurationValue(ENDPOINT_EPISODE_KEY))
                .then()
                .extract().body().jsonPath().getInt("info.count");
    }

    @Когда("отправить Get запрос на адрес последней серии и получить ID последнего персонажа")
    public void getLastCharacterIdFromEpisode() {
        setSpecification(rickAndMortyRequestSpec(), responseSpecification());
        ArrayList<String> charactersFromEpisode = given()
                .get(getConfigurationValue(ENDPOINT_EPISODE_KEY) + lastEpisodeId)
                .then()
                .extract().body().jsonPath().getJsonObject(CHARACTERS);

        String lastCharacter = charactersFromEpisode.get(charactersFromEpisode.size() - 1);
        lastCharacterId = Integer.parseInt(lastCharacter.split("/")[5]);
    }

    @Когда("отправить Get запрос на адрес с информацией о последнем персонаже в последней серии")
    public void getLastCharacterInfo() {
        lastCharacter = given()
                .get(getConfigurationValue(ENDPOINT_CHARACTER_KEY) + lastCharacterId)
                .then()
                .extract().body().as(Result.class);
    }

    @Тогда("придет ответ в виде тела с информацией о первом Морти")
    public void checkInfoAboutMorty() {
        assertEquals("Morty Smith", firstMorty.getName());
        assertEquals("Citadel of Ricks", firstMorty.getLocation().getName());
        assertEquals("Alive", firstMorty.getStatus());
        assertEquals("Human", firstMorty.getSpecies());
        assertEquals("Male", firstMorty.getGender());
    }

    @Тогда("сравнить локацию Морти и персонажа")
    public void checkLocation() {
        assertEquals(firstMorty.getLocation(), lastCharacter.getLocation(), "Локации не совпадают");
    }

    @Тогда("сравнить расу Морти и персонажа")
    public void checkSpecies() {
        assertEquals(firstMorty.getSpecies(), lastCharacter.getSpecies(), "Расы не совпадают");
    }
}