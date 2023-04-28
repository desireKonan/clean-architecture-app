package com.edenia.app.core.application.ports.input;

import com.edenia.app.core.domain.model.Notes;

import java.util.List;
import java.util.Optional;

public interface FindNoteInputPort {

    List<Notes> fetchNotes();

    Optional<Notes> fetchNote(Long id) throws Exception;
}
