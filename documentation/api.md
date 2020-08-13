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
    - **http://localhost:8080/api/schedule_user_cell/filteredPercentage** - *Получить сведения о процентноим отношении посещенных занятий по заданным фильрам*
    
- SPBU API
    -GET
    - **http://localhost:8080/spbu/divisions** - *Получить список направлений подготовки*
    - **http://localhost:8080/spbu/division/MATH/programs** - *Получить список програм обучения для направления MATH*
    - **http://localhost:8080/spbu/program/10035/groups** - *Получить список студенческих групп для программы обучения с Id 10035*
    - **http://localhost:8080/spbu/group/249301/events"** - *Получить сведения о занятиях группы с Id 249301 на следующую неделю*
    - **http://localhost:8080/spbu/group/249301/events/25-03-2020/14-04-2020"** - *Получить сведения о занятиях группы с Id 249301 за период с 25-03-2020 по 14-04-2020*
    - **http://localhost:8080/spbu/group/name/18.М02-х/events"** - *Получить сведения о занятиях группы 18.М02-х на следующую неделю*
    - **http://localhost:8080/spbu/group/name/18.М02-х/events/25-03-2020/14-04-2020"** - *Получить сведения о занятиях группы 18.М02-х за период с 25-03-2020 по 14-04-2020*
    - **http://localhost:8080/spbu/division/MATH/saveAllGroups"** - *Записать все группы направления MATH в БД*
   
    

    