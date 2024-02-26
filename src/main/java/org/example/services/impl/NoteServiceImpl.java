package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.example.models.Note;
import org.example.repositories.NoteRepo;
import org.example.services.NoteService;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис заметок для передачи запросов в репозиторий
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepo noteRepo;

    /**
     * Метод получения списка всех задач
     *
     * @return список заметок
     */
    @Override
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    /**
     * Метод сохранения заметки
     *
     * @param note новая заметка
     * @return сохраненная заметка
     */
    @Override
    public Note createNote(Note note) {
        note.setDateCreate(LocalDateTime.now());
        return noteRepo.save(note);
    }

    /**
     * Метод получения заметки по id
     *
     * @param id id заметки
     * @return искомая заметка
     */
    @Override
    public Note getNoteById(Long id) {
        return noteRepo.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменения заметки по id
     *
     * @param id            id заметки
     * @param changeHeading заголовок заметки
     * @param changeContent содержимое заметки
     * @return обновленная заметка
     */
    @Override
    public Note updateNote(Long id, String changeHeading, String changeContent) {
        Note noteById = getNoteById(id);
        noteById.setHeading(changeHeading);
        noteById.setContent(changeContent);
        return noteRepo.save(noteById);
    }

    /**
     * Метод удаления заметки по id
     *
     * @param id id заметки
     */
    @Override
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepo.delete(noteById);
    }
}
