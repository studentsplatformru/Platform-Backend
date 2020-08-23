package ru.studentsplatform.backend.endpoint.mapper;

import org.springframework.stereotype.Component;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramFollowerDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramFollowerMapper {

    private final SpbuTeamRepository teamRepository;

    public TelegramFollowerMapper(SpbuTeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TelegramFollowerDTO telegramFollowerToTelegramFollowerDTO(TelegramFollower follower){
        TelegramFollowerDTO dto = new TelegramFollowerDTO();
        dto.setId(follower.getTelegramId());
        dto.setTeamName(follower.getTeam().getName());
        return dto;
    }

    public TelegramFollower telegramFollowerDTOToTelegramFollower(TelegramFollowerDTO dto){
        TelegramFollower follower = new TelegramFollower();
        follower.setTelegramId(dto.getId());
        follower.setTeam(teamRepository.findByName(dto.getTeamName()));
        return follower;
    }

    public List<TelegramFollowerDTO> listTelegramFollowerToTelegramFollowerDTO(List<TelegramFollower> followers){
        List<TelegramFollowerDTO> result = new ArrayList<>();
        for (TelegramFollower follower : followers) {
            result.add(this.telegramFollowerToTelegramFollowerDTO(follower));
        }
        return result;
    }

}
