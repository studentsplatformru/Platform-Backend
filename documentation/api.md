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
    - **http://localhost:8080/api/task/filter** - *Получить список task по заданным фильтрам*
    - **http://localhost:8080/api/task/1/file/1/** - *Получить файл с Id = 1, прикрепленный к task c Id = 2*
    