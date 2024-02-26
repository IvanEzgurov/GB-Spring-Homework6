package org.example.repositories;

import org.example.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с БД H2
 */
@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    /**
     * Собственный метод получения списка заметок по статусу
     * @param id id заметки
     * @return Optional заметку
     */
    Optional<Note> findById(Long id);
}
