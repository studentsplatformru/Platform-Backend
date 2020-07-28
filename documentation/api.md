### [Главная](../README.md)
***
#### [Оглавление](main.md)
***
##### Api

###### Утилитное

- Log
    - **http://localhost:8080/api/system/log** - *просмотр всего файла log*
    - **http://localhost:8080/api/system/log?lines=100** - *просмотр последних 100 строк файла log*
    
###### Основное
- Task
    - **http://localhost:8080/task** - *Добавить task*
    - **http://localhost:8080/task/1/file** - *Приложить файл к task c Id 1*
    - **http://localhost:8080/task/1/file/2/** *Загрузить файл, прикрепленный к task c Id 1, имеющий Id = 2*