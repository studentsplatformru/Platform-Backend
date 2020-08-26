package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramFollowerDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;


@Profiled
@Mapper(componentModel = "spring", uses = {SpbuTeamRepository.class})
@Component
public abstract class TelegramFollowerMapper {

    @Autowired
    protected SpbuTeamRepository spbuTeamRepository;

    @Mappings({
            @Mapping(target = "id", source = "follower.telegramId"),
            @Mapping(target = "teamName",
                    expression = "java(follower.getTeam().getName())")
    })
    public abstract TelegramFollowerDTO telegramFollowerToTelegramFollowerDTO(TelegramFollower follower);

    @Mappings({
            @Mapping(target = "telegramId", source = "dto.id"),
            @Mapping(target = "team",
                    expression = "java(spbuTeamRepository.findByName(dto.getTeamName()))")
    })
    public abstract TelegramFollower telegramFollowerDTOToTelegramFollower(TelegramFollowerDTO dto);


    public abstract List<TelegramFollowerDTO> listTelegramFollowerToTelegramFollowerDTO(
            List<TelegramFollower> followers);

}
