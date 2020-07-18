package ru.studentsplatform.backend.tlgrmbot.bot;

public enum Text {
    START_INFO("Описание доступного функционала бота."),
    START_MESSAGE("Start Message"), //TODO дополнить

    SCHEDULE_INFO("Получить расписание за текущую неделю."),
    SCHEDULE_SUCCESS("Вот это расписание!"),
    SCHEDULE_FAILURE("Что-то пошло не так. Возможно, неправильно указаны данные."),

    UNIVERSITY_STATE("Выберите университет:"),
    DIRECTION_STATE("Выберите направление:"),
    GROUP_STATE("Выберите группу:");


    private final String value;

    private Text(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
