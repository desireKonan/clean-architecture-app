package com.edenia.app.core.application.ports.output;

import com.edenia.app.core.domain.model.Notes;

import java.util.List;
import java.util.Optional;

public interface NoteOutputPort {
    List<Notes> findAllNotes();

    Optional<Notes> getNoteById(long id);

    Notes createNote(Notes noteRequest);

    Notes updateNote(long id, Notes noteRequest);

    void deleteNote(long id);
}
