package ru.studentsplatform.backend.dbtest;

public class SpringTestConfig {

    private final static String FILLING_STRING = "INSERT INTO university VALUES (1, 'я', 'Также я', 'Санкт-Петербургский государственный университет');" +
            "INSERT INTO university VALUES (2, 'я', 'Также я', 'Университет ИТМО');" +
            "INSERT INTO faculty VALUES (1, 'я', 'Также я', 'Грифендор');" +
            "INSERT INTO faculty VALUES (2, 'я', 'Также я', 'Слизерин');" +
            "INSERT INTO department VALUES (1, 'я', 'Также я', 'Зельеварение');" +
            "INSERT INTO department VALUES (2, 'я', 'Также я', 'Колдунство');" +
            "INSERT INTO direction VALUES (1, 'я', 'Также я', 'Волшебники');" +
            "INSERT INTO direction VALUES (2, 'я', 'Также я', 'Дворники');" +
            "INSERT INTO team VALUES (1, 'я', 'Также я', 1, 'Г-1', 1);" +
            "INSERT INTO team VALUES (2, 'я', 'Также я', 2, 'C-1', 2);" +
            "INSERT INTO usr values (1, 'я', 'Также я', 'harry-potter@owl.ru', 'snitch1', 'Студент', 1);" +
            "INSERT INTO usr values (2, 'я', 'Также я', 'Drako1337Malfoy@owl.mail', 'HarryDork', 'Студент', 1);" +
            "INSERT INTO usr values (3, 'я', 'Также я', 'Dumbledore@owl.mail', 'DontForget', 'Преподаватель', 1);" +
            "INSERT INTO subject VALUES (1, 'я', 'Также я', 'Защита от темной магии');" +
            "INSERT INTO subject VALUES (2, 'я', 'Также я', 'Хоровое пение');" +
            "INSERT INTO place_study VALUES ('я', 'Также я', 1,1,1,1,1,1,1);" +
            "INSERT INTO place_study VALUES ('я', 'Также я', 2,2,2,2,2,2,2);" +
            "INSERT INTO user_info VALUES ('я', 'Также я', 'Гарри', 'Поттер', 'Безбатькович','8-228-111',1);" +
            "INSERT INTO user_info VALUES ('я', 'Также я', 'Драко', 'Малфой', 'Люциус','8-001-200',2);" +
            "INSERT INTO discipline VALUES (1, 'я', 'Также я', 1,1,1);" +
            "INSERT INTO discipline VALUES (2, 'я', 'Также я', 2,2,2);" +
            "INSERT INTO schedule_cell VALUES (" +
            "1, 'я', 'Также я', '2004-10-19 10:23:54+02', '2004-10-19 11:53:54+02','Лекция', 1, 1);" +
            "INSERT INTO schedule_cell VALUES (" +
            "2, 'я', 'Также я', '2004-10-12 10:23:54+02', '2004-10-20 11:53:54+02','Практика',2, 2);" +
            "INSERT INTO schedule_user_cell VALUES (1, 'я', 'Также я', 'Студент',1,1,1);" +
            "INSERT INTO schedule_user_cell VALUES (2, 'я', 'Также я', 'Преподаватель',2,2,2);" +
            "INSERT INTO task VALUES (1, 'я', 'Также я', '2004-10-19 11:53:54+02',false,null,'Презентация',1);" +
            "INSERT INTO task VALUES (2, 'я', 'Также я', '2005-10-19 11:53:54+02',true,5,'Выкинуть мусор',2);" +
            "INSERT INTO task_attachment VALUES (1, 'я', 'Также я', null,'image/jpeg','Картиночка',1);" +
            "INSERT INTO task_attachment VALUES (2, 'я', 'Также я', null,'image/jpeg','Пикча',2);";

    static String getFillingString() {
        return FILLING_STRING;
    }
}
