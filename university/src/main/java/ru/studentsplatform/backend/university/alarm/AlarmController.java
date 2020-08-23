package ru.studentsplatform.backend.university.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class AlarmController {

    @Autowired
    private SpbuTelegramFollowerService followerService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private SpbuEventRepository eventRepository;

    @Autowired
    private NotifyService notifyService;

    @Async
    public void activateEventNotifyAlarm(){
        List<TelegramFollower> followers = followerService.getAll();
        LocalDate date = LocalDate.of(2020, 3, 27);
        String template;

        while (!followers.isEmpty()){

            SpbuTeam team = followers.get(0).getTeam();

            List<SpbuEvent> eventsByDay = eventRepository.findByDateAndTeam(date, team);

            Collections.sort(eventsByDay);

            template =
                    templateService.getTemplate(
                            MessageType.EVENTS_DAY_NOTIFICATION,
                            NotificationType.Telegram,
                            eventsByDay);

            List<TelegramFollower> followerToSend = new ArrayList<>();

            for (TelegramFollower f : followers){
                if (f.getTeam().equals(team)){
                    followerToSend.add(f);
                }
            }

            followers.removeAll(followerToSend);

            List<TelegramMessageDTO> telegramMessageDTOS = this.mappedIntoDTO(followerToSend, template);

            notifyService.sendListMessagesTelegram(telegramMessageDTOS);
        }
    }


    private List<TelegramMessageDTO> mappedIntoDTO(List<TelegramFollower> followers, String template){
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
