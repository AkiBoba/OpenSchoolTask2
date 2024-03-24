Для запуска приложения
1) Необходимо запустить docker-compose.yml что бы активировать брокер kafka.
2) Запустить отдельно модуль metricsProducer и отдельно модуль metricsConsumer.

    Протестировать можно, запустив документацию swagger-openapi.

    Документация openapi:
    
    http://localhost:8080/swagger-ui/index.html#/  metricsProducer
    
    http://localhost:8085/swagger-ui/index.html#/  metricsConsumer
3) Важно!
- Для отправки собственной метрики через POST /metrics необходимо, сначала зарегистрироваться через /api/v1/addNewUser , можно воспользоваться документацией http://localhost:8080/swagger-ui/index.html#/registration-controller/addNewUser
- Получить токен для доступа к API и отправить метрику по адресу (здесь, удобнее использовать postman) /api/v1/metrics добавив токен в authorization с типом Bearer Token

Коммит 1
- Создание микросервисов Metrics Producer и Metrics Consumer.
- Получение и сохранение в БД Producer метрик из приложения.
- Отправка агрегированных по среднему значению и наименованию метрик, за последние 20 секунд, в топик metrics-topic.
Коммит 2
- Реализация получения микросервисом Metrics Consumer метрик из топика metrics-topic.
- Сохранение полученных метрик в БД Consumer.
- Реализация REST API GET /metrics: Получение списка всех метрик.
- GET /metrics/{id}: Получение конкретной метрики по ее идентификатору.
- Реализация возможности фильтрации метрик по времени и (или) наименованию.


