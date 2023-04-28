package com.edenia.app.core.application.usecases;

import com.edenia.app.core.application.ports.input.SaveNoteInputPort;
import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.infrastructure.mapper.NoteMapper;
import com.edenia.app.core.application.ports.output.NoteOutputPort;
import com.edenia.app.core.domain.request.NoteRequest;

public class CreateNoteUseCase implements SaveNoteInputPort {
    private NoteOutputPort noteOutputPort;
    private final NoteMapper noteMapper;

    public CreateNoteUseCase(NoteOutputPort noteInputPort, NoteMapper noteMapper) {
        this.noteOutputPort = noteInputPort;
        this.noteMapper = noteMapper;
    }

    @Override
    public Notes saveNote(NoteRequest notes) {
        Notes note = this.noteMapper.mapTo(notes);
        return this.noteOutputPort.createNote(note);
    }
}
