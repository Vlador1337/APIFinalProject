### ������ ������
```sh
mvn clean test
```

### ���������� ���������� ������
```sh
mvn allure:serve
```

### ������� ������
��������� ��������� � application.properties
���:
```sh
mvn clean test -DbaseUrlRickAndMorty=https://rickandmortyapi.com/api/ -DbaseUriReqRes=https://reqres.in/ -DbasePathReqRes=api/users -DendpointCharacter=character/ -DendpointEpisode=episode/
```
