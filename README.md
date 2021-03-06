# Запуск

1. Нужно запустить docker-compose up в корне проекта
2. Запустить SpringBootApplication

# Комментарии
1. Миграция происходит с помощью liquibase при запуске SpringBootApplication
2. Обращение к бд происходит через JPA репозитории и самописные SQL запросы в аннотации `@Query`
3. Так как дополнительным требованием является вывод победителей, то вместо удаления участников из базы им присваивается статус неактивный - чтобы не хранить всю информацию о победителях в двух таблицах и иметь возможность вывести данные об участнике лотерее в запросе получения всех победителей 

Для добавления пользователя требуется отправлять JSON на адрес `http://localhost:8080/lottery/participant` в следующем формате:

```json
{
    "name": "Winner Name",
    "city": "cityName",
    "age": 21
}
``` 

Возможные исключения:

`NotEnoughParticipantException` - HTTP 403 Forbidden, в случае если при старте лотереи участников меньше двух

`ParticipantNotFoundException` - HTTP 500 Internal Server Error, в случае если не найден пользователь (статус таков, на случай ошибок на Backend и возможной отладки)

В случае недоступности ресурса `Random.org` ошибки не происходит - используется библиотека Random