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
    -POST
    - **http://localhost:8080/api/task/1/file** - *Прикрепить файл к задаче с Id = 1* (файлы прикреплять с именем значения "file")
    - **http://localhost:8080/api/task/cell/1/create** - *Создаёт задачу, прикреплённую к ячейке пользовательского расписания с Id = 1*
    
    -GET
    - **http://localhost:8080/api/task/1/** - *Получить сведения о task c Id = 1*
    - **http://localhost:8080/api/task/filter** - *Получить сведения о task по заданным фильтрам*
    - **http://localhost:8080/api/task/1/file/1/** - *Получить файл с Id = 1, прикрепленный к task c Id = 2*
    
- ScheduleUserCell
    -POST
    
    -GET
    - **http://localhost:8080/api/schedule_user_cell/getFiltered** - *Получить сведения о scheduleUserCell по заданным фильтрам*
    - **http://localhost:8080/api/schedule_user_cell/filteredPercentage** - *Получить сведения о процентном отношении посещенных занятий по заданным фильтрам*
    
- SPBU API
    -GET
    - **http://localhost:8080/spbu/divisions** - *Получить список направлений подготовки*
    - **http://localhost:8080/spbu/division/MATH/programs** - *Получить список программ обучения для направления MATH*
    - **http://localhost:8080/spbu/program/10035/groups** - *Получить список студенческих групп для программы обучения с Id 10035*
    - **http://localhost:8080/spbu/group/17.Б09-ю/events** - *Получить сведения о занятиях на текущую неделю для группы 17.Б09-ю*
    - **http://localhost:8080/spbu/group/18.М02-х/events/25-03-2020** - *Получить сведения о занятиях группы 18.М02-х за 25-03-2020*
    - **http://localhost:8080/spbu/group/18.М02-х/events/25-03-2020/14-04-2020** - *Получить сведения о занятиях группы 18.М02-х за период с 25-03-2020 по 14-04-2020*
    - **http://localhost:8080/spbu/division/MATH/saveAllGroups** - *Записать все группы направления MATH в БД*
    - **http://localhost:8080/spbu/saveAllGroups** - *Записать все группы в БД*
    - **http://localhost:8080/spbu/stressTest/100** - *Произвести стресс-тест соединения к БД СПБГУ на 100 запросов*
    
- Telegram Follower API
 - GET
 - **http://localhost:8080/api/telegram/follower** - *Получить список всех подписчиков*
 - **http://localhost:8080/api/telegram/follower/{id}** - *Получить подписчика по id*
 - POST
 - **http://localhost:8080/api/telegram/follower/create** - *Создать сущность подписчика. Тело вызова принимается 
 в виде сущности TelegramFollowerDTO*
 - PUT
 - **http://localhost:8080/api/telegram/follower/id** - *Заменить поля в сущности подписчика с определённым id.
 Тело вызова принимается в виде сущности TelegramFollowerDTO*
 -DELETE
 - **http://localhost:8080/api/telegram/follower/id** - *Удаляет запись с id.

    