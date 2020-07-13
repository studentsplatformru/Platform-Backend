package ru.studentsplatform.backend.service.spbu.finder;

/**
 * Интерфейс представляет поиск по странице произвольного университета ссылки на расписание за неделю.
 */
public interface ScheduleUrlFinder {
    /**
     * Метод возвращает ссылку на определённую неделю расписания.
     *
     * @param direction Название направления подготовки на английском.
     * @param groupName Полное имя группы.
     * @return Ссылка на расписание для конкретной группы.
     */
    String findScheduleLink(String direction, String groupName);
}
