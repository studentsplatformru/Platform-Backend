package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramFollowerDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.endpoint.mapper.TelegramFollowerMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.TelegramFollowerCRUDController;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.entities.model.user.TelegramFollower;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTelegramFollowerService;

import java.util.List;

import static ru.studentsplatform.backend.system.helper.ControllerUtils.handleExceptions;

/**
 * Реализация {@link TelegramFollowerCRUDController}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.08.2020).
 */
@Profiled
@RestController
@RequestMapping(TelegramFollowerCRUDController.BASE_URL)
public class TelegramFollowerCRUDControllerImpl implements TelegramFollowerCRUDController {

    private final SpbuTelegramFollowerService telegramFollowerService;

    private final SpbuTeamRepository teamRepository;

    private final TelegramFollowerMapper mapper;

    /**
     *
     * @param telegramFollowerService сервис для работы с подписчиками.
     * @param teamRepository репозиторий групп СПБУ.
     * @param mapper вспомогательный класс работы с {@link TelegramFollowerDTO}
     */
    public TelegramFollowerCRUDControllerImpl(SpbuTelegramFollowerService telegramFollowerService,
                                              SpbuTeamRepository teamRepository, TelegramFollowerMapper mapper) {
        this.telegramFollowerService = telegramFollowerService;
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @PostMapping("/create")
    @Override
    public ResponseEntity<TelegramFollowerDTO> create(@RequestBody TelegramFollowerDTO telegramFollowerDTO) {
        SpbuTeam spbuTeam = teamRepository.findByName(telegramFollowerDTO.getTeamName());
        TelegramFollower follower = telegramFollowerService.create(telegramFollowerDTO.getId(), spbuTeam);
        return ResponseEntity.ok(mapper.telegramFollowerToTelegramFollowerDTO(follower));
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<TelegramFollowerDTO> getById(@PathVariable Long id) throws Fault {
        return handleExceptions(() -> {
            TelegramFollower follower = telegramFollowerService.getById(id);
            if (follower == null) {
                throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, id);
            }
            return ResponseEntity.ok(mapper.telegramFollowerToTelegramFollowerDTO(follower));
        });
    }

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @Override
    public ResponseEntity<List<TelegramFollowerDTO>> getAll() {
        return ResponseEntity.ok(
                mapper.listTelegramFollowerToTelegramFollowerDTO(telegramFollowerService.getAll()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TelegramFollowerDTO> update(@RequestBody TelegramFollowerDTO telegramFollowerDTO,
                                                      @PathVariable Long id) {
        telegramFollowerService.delete(id);
        return this.create(telegramFollowerDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        telegramFollowerService.delete(id);
        return ResponseEntity.ok(true);
    }

}
