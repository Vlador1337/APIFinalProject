#language: ru
@Test
Функциональность: Rick and Morty - Сравнение Морти и последнего персонажа из последний серии

  Сценарий: Информация о Морти Смит
    Если получить информацию о всех "Morty Smith"
    И отправить Get запрос на endpoint characters с ID первого Морти
    Тогда придет ответ в виде тела с информацией о первом Морти

  Сценарий: Сравнение локаций первого Морти и последнего персонажа из последней серии
    Если получить информацию о всех "Morty Smith"
    И отправить Get запрос на endpoint characters с ID первого Морти
    И получить ID последней серии
    И отправить Get запрос на адрес последней серии и получить ID последнего персонажа
    И отправить Get запрос на адрес с информацией о последнем персонаже в последней серии
    Тогда сравнить локацию Морти и персонажа

  Сценарий: Сравнение расы первого Морти и последнего персонажа из последней серии
    Если получить информацию о всех "Morty Smith"
    И отправить Get запрос на endpoint characters с ID первого Морти
    И получить ID последней серии
    И отправить Get запрос на адрес последней серии и получить ID последнего персонажа
    И отправить Get запрос на адрес с информацией о последнем персонаже в последней серии
    Тогда сравнить расу Морти и персонажа