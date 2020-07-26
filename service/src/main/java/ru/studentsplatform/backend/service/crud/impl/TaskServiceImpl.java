package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.domain.repository.TaskRepository;
import ru.studentsplatform.backend.service.crud.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task create(Task newEntity) {
        return repository.saveAndFlush(newEntity);
    }

    @Override
    public Task getById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Override
    public Task update(Task updatedEntity, Long id) {
        updatedEntity.setId(id);
        return repository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
