#language: ru
@Test
Функциональность: ReqRes - создание нового пользователя

  Сценарий: Отправка запроса для создания нового пользователя
    Если создать нового пользователя с именем - "Tomato" и местом работы - "Eat market" и отправить Post запрос
    Тогда придет ответ с кодом "201" и тело с информацией