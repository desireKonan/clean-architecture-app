package com.edenia.app.core.application.usecases;

import com.edenia.app.core.application.ports.output.NoteOutputPort;
import com.edenia.app.core.application.ports.input.DeleteNoteInputPort;

public class DeleteNoteUseCase implements DeleteNoteInputPort {

    private NoteOutputPort noteOutputPort;

    public DeleteNoteUseCase(NoteOutputPort noteInputPort) {
        this.noteOutputPort = noteInputPort;
    }

    @Override
    public void delete(long id) {
        this.noteOutputPort.deleteNote(id);
    }
}
