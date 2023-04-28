package com.edenia.app.core.application.ports.input;

import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.core.domain.request.NoteRequest;

public interface UpdateNoteInport {
    Notes findNoteById(long id) throws Exception;
    Notes updateNotes(Long id, NoteRequest notes);
}
