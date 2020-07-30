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
    - **http://localhost:8080/user/3/schedule/cell/2/task/1/** - *Получить сведения о task c Id = 1, привязанной к ячейке с Id = 2, принадлежащей пользователю с Id = 3*
    - **http://localhost:8080/user/2/schedule/cell/1/tasks** - *Получить сведения о всех task, привязанных к ячейке с Id = 1, принадлежащих пользователю с Id = 2*
    - **http://localhost:8080/user/3/schedule/cell/2/task/1/file** - *Приложить файлы к task c Id = 1, привязаной к ячейке с Id = 2, принадлежащей пользователю с Id = 3*
    - **http://localhost:8080/user/3/schedule/cell/2/task/1/file/1/** - *Получить файл с Id = 1, прикрепленный к task c Id = 2, привязаной к ячейке с Id = 3, принадлежащей пользователю с Id = 4*