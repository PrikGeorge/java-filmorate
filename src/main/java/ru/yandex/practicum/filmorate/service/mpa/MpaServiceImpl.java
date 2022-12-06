package ru.yandex.practicum.filmorate.service.mpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.EntityNotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import java.util.List;

/**
 * @project java-filmorate
 * @auther George Prikashchenkov on 05.12.2022
 */

@Slf4j
@Service
public class MpaServiceImpl implements MpaService {

    private final MpaStorage storage;

    @Autowired
    public MpaServiceImpl(MpaStorage storage) {
        this.storage = storage;
    }

    /**
     * Поиск всех рейтинов
     *
     * @return List
     */
    @Override
    public List<Mpa> getAll() {
        return storage.getAll();
    }

    /**
     * Получить рейтинг по индентификатору
     *
     * @param id
     * @return Mpa
     */
    @Override
    public Mpa findById(Long id) {
        return storage.findById(id).orElseThrow(() -> {
            log.info("Ошибка при поиске рейтинга.");
            throw new EntityNotFoundException("Рейтинг с id=" + id + " не найден.");
        });
    }
}
