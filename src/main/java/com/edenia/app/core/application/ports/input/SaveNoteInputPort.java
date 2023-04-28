package com.edenia.app.core.application.ports.input;

import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.core.domain.request.NoteRequest;

public interface SaveNoteInputPort {
    Notes saveNote(NoteRequest notes);
}
