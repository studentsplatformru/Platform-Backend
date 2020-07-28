[Главная](../README.md)
***
## Взаимодействие с Flyway
Получение всей информации и взаимодействие с функционалом 
происходит посредством плагина в репозитории *migration*.
Следовательно путь, от корня проекта до репозитория выглядит как:
  
**../Platform-Backend/migration**

#### Плагин поддерживает следующие комманды:
 - mvn flyway:info - информация о текущих миграциях. 
 Напрямую можно ознакомиться в таблице *flyway_schema_history*.
  
 - mvn flyway:migrate - произвести миграцию БД.
 - mvn flyway:drop - очистить содержимое схем.
 - mvn flyway:validate - Проверяет примененные миграции
  на доступные в classpath.
 - mvn flyway:baseline - инициализации БД
  до верии в переменной baselineVersion указанной
  в [конфигурациях][].
 - mvn flyway:repair - Восстанавливает таблицу *flyway_schema_history*.
 
Необходимые настройки Flyway можно сделать в [конфигурациях][],
либо же настроить плагин можно в 
[pom](../migration/pom.xml)

[Подробная инструкция](https://flywaydb.org/documentation/maven/)
по плагину.

 
[конфигурациях]: ../endpoint/src/main/resources/application.yml