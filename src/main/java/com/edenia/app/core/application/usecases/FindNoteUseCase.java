package com.edenia.app.core.application.usecases;

import com.edenia.app.core.application.ports.output.NoteOutputPort;
import com.edenia.app.core.application.ports.input.FindNoteInputPort;
import com.edenia.app.core.domain.exception.NotesNotFoundException;
import com.edenia.app.core.domain.model.Notes;

import java.util.List;
import java.util.Optional;

public class FindNoteUseCase implements FindNoteInputPort {

    private NoteOutputPort noteOutputPort;

    public FindNoteUseCase(NoteOutputPort noteInputPort) {
        this.noteOutputPort = noteInputPort;
    }

    @Override
    public List<Notes> fetchNotes() {
        return noteOutputPort.findAllNotes();
    }

    @Override
    public Optional<Notes> fetchNote(Long id) throws NotesNotFoundException {
        Optional<Notes> note = noteOutputPort.getNoteById(id);
        if(!note.isPresent()) {
            Notes noteFound = note.get();
            throw new NotesNotFoundException(noteFound.getId());
        }
        return note;
    }
}
