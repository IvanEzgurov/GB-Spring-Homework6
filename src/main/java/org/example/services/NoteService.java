package org.example.services;

import org.example.models.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    Note createNote(Note note);
    Note getNoteById(Long id);
    Note updateNote(Long id, String changeHeading, String changeContent);
    void deleteNote(Long id);
}
