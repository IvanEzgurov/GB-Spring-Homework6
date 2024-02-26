package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.models.Note;
import org.example.services.NoteService;

/**
 * Класс - контроллер, используется для обработки запросов
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    /**
     * Метод обработки GET запроса без параметров:
     * //localhost:8080/notes
     *
     * @return список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * POST метод обработки запроса добавления заметки через параметр:
     * //localhost:8080/notes
     *
     * @param note новая заметка, передается через body
     * @return новая заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    /**
     * GET метод обработки запроса списка заметок с указанным статусом:
     * localhost:8080/notes/{id}
     *
     * @param id id заметки
     * @return искомая заметка
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * PUT метод обработки запроса изменения заметки по id:
     * //localhost:8080/notes/{id}?heading={heading}&content={content}
     *
     * @param id      id заметки
     * @param heading заголовок заметки, передается при помощи параметра
     * @param content содержимое заметки, передается при помощи параметра
     * @return измененная заметка
     */
    @PutMapping("{id}")
    @ResponseBody
    public ResponseEntity<Note> updateProduct(@PathVariable("id") Long id,
                                              @RequestParam("heading") String heading,
                                              @RequestParam("content") String content) {
        return new ResponseEntity<>(noteService.updateNote(id, heading, content), HttpStatus.OK);
    }

    /**
     * DELETE метод обработки запроса удаления заметки по id:
     * //localhost:8080/notes/{id}
     *
     * @param id id заметки
     * @return void
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
