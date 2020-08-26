package ru.studentsplatform.backend.university.alarm;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.TemplateService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTelegramFollowerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация задачи "Будильник".
 * Отправка сообщений подписанным пользователям за 2 часа до занятий.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.08.2020).
 */
@Controller
public class AlarmScheduleTask {

    private final SpbuTelegramFollowerService followerService;

    private final TemplateService templateService;

    private final SpbuEventRepository eventRepository;

    private final NotifyService notifyService;

    /**
     * @param notifyService сервис уведомлений.
     * @param followerService сервис для работы с подписчиками.
     * @param templateService сервис для получения сообщения.
     * @param eventRepository сервис для получения занятий.
     */
    public AlarmScheduleTask(NotifyService notifyService, SpbuTelegramFollowerService followerService,
                             TemplateService templateService, SpbuEventRepository eventRepository) {
        this.notifyService = notifyService;
        this.followerService = followerService;
        this.templateService = templateService;
        this.eventRepository = eventRepository;
    }

    /**
     * Запуск метода в 5 утра каждый день.
     */
    @Scheduled(cron = "0 0 5 * * *")
    public void activateEventNotifyAlarm() {
        List<TelegramFollower> followers = followerService.getAll();
        LocalDate date = LocalDate.now();
        String template;

        while (!followers.isEmpty()) {
            // получение названия группы
            SpbuTeam team = followers.get(0).getTeam();

            List<TelegramFollower> followerToSend = new ArrayList<>();

            // получение подписчиков с одинаковой группой
            for (TelegramFollower f : followers) {
                if (f.getTeam().equals(team)) {
                    followerToSend.add(f);
                }
            }

            // удаление подписчиков из общего пула
            followers.removeAll(followerToSend);

            // получение расписания для текущей группы
            List<SpbuEvent> eventsByDay = eventRepository.findByDateAndTeam(date, team);

            // в случае отсутствия расписания переходим к следующей группе
            if (eventsByDay.isEmpty()) {
                continue;
            }

            // сортируем расписание для выдачи в правильном порядке
            eventsByDay.sort(Comparator.comparing(SpbuEvent::getStartTime));

            // получение конкретной даты отправки
            LocalDateTime sendTime = eventsByDay.get(0)
                    .getStartTime().atDate(date).minusHours(2);

            // получение сообщения для отправки
            template =
                    templateService.getTemplate(
                            MessageType.EVENTS_DAY_NOTIFICATION,
                            NotificationType.Telegram,
                            eventsByDay);

            // преобразование в сущность для отправки
            List<TelegramMessageDTO> telegramMessageDTOS = mappedIntoDTO(followerToSend, template);

            // отправка
            notifyService.sendListMessagesTelegram(telegramMessageDTOS, sendTime);
        }
    }


    /**
     * @param followers подписчики для обработки.
     * @param template сообщение для отправки.
     * @return пакет сообщений для отправки через Telegram.
     */
    private static List<TelegramMessageDTO> mappedIntoDTO(List<TelegramFollower> followers, String template) {
        List<TelegramMessageDTO> messages = new ArrayList<>();

        for (TelegramFollower follower : followers) {

            TelegramMessageDTO messageDTO = new TelegramMessageDTO();
            messageDTO.setId(follower.getTelegramId());
            messageDTO.setText(template);
            messages.add(messageDTO);
        }

        return messages;
    }
}


