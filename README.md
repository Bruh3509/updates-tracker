# Link Tracker



Приложение для отслеживания обновлений контента по ссылкам.
При появлении новых событий отправляется уведомление в Telegram.

Проект написан на `Java 21` с использованием `Spring Boot 3`.

Проект состоит из 2-х приложений:
* Bot - отвечает за взаимодействие с пользователем
* Scrapper - серверная часть, проверяет ссылки на обновления, работает с БД

Для работы требуется БД `PostgreSQL`. Присутствует опциональная зависимость на `Kafka`.
В проекте есть возможность выбора одного из 3 доступных способов взаимодействия с БД:
* Голый JDBC
* Spring Data JPA (Hibernate)
* JOOQ (кодогенерация)

Для сбора метрик и мониторинга используется `Prometheus/Grafana`

![requests_sum](https://github.com/Bruh3509/updates-tracker/assets/69002959/9d6f68f1-2058-4961-833e-280d6015cf3d)
![requests_count](https://github.com/Bruh3509/updates-tracker/assets/69002959/28304a5d-b5c0-4dd8-847f-da45211fd44e)
![memory_used](https://github.com/Bruh3509/updates-tracker/assets/69002959/db0efc5e-1833-41fc-8e81-ef722560ae1b)
![gc](https://github.com/Bruh3509/updates-tracker/assets/69002959/efeb0249-e926-4ffb-8406-454046267d50)

![entire_dashboard](https://github.com/Bruh3509/updates-tracker/assets/69002959/ffa7adf9-50cd-4739-bf9a-05ea6bc4904d)
![bussiness_metric_messages](https://github.com/Bruh3509/updates-tracker/assets/69002959/9b04b55a-ab44-459a-a430-decb325b911f)
