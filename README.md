### Запуск тестов
```sh
mvn clean test
```

### Построение локального отчета
```sh
mvn allure:serve
```

### Входные данные
Эндпоинты заполнять в application.properties
или:
```sh
mvn clean test -DbaseUrlRickAndMorty=https://rickandmortyapi.com/api/ -DbaseUriReqRes=https://reqres.in/ -DbasePathReqRes=api/users -DendpointCharacter=character/ -DendpointEpisode=episode/
```
