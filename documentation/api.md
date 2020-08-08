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
    - **http://localhost:8080/api/task/cell/1/** - *Получить сведения о всех task, привязанных к ячейке с Id = 1*
    - **http://localhost:8080/api/task/user/1/isDone/false** - *Поиск невыполненных заданий у пользователя с Id = 1*
    - **http://localhost:8080/api/task/user/1/semester/1/** - *Поиск заданий у пользователя с Id = 1 в первом семестре*
    - **http://localhost:8080/api/task/user/1/subject/2/** - *Поиск заданий у пользователя с Id = 1 по предмету с Id = 2*
    - **http://localhost:8080/api/task/subject/1/group/1** - *Поиск заданий по предмету с Id = 1 у группы студенов с Id = 1*  
    - **http://localhost:8080/api/task/1/file/1/** - *Получить файл с Id = 1, прикрепленный к task c Id = 2*
    
- ScheduleUserCell
    -POST
    
    -GET
    - **http://localhost:8080/api/schedule_user_cell/getFiltered** - *Получить сведения о scheduleUserCell по заданным фильтрам*
    - **http://localhost:8080/api/schedule_user_cell/filteredPercentage** - *Получить сведения о процентноим отношении посещенных занятий по заданным фильрам*

    