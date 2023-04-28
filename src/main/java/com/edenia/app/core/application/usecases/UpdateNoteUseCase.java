package com.edenia.app.core.application.usecases;

import com.edenia.app.core.domain.exception.NotesNotFoundException;
import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.infrastructure.mapper.NoteMapper;
import com.edenia.app.core.application.ports.output.NoteOutputPort;
import com.edenia.app.core.application.ports.input.UpdateNoteInport;
import com.edenia.app.core.domain.request.NoteRequest;

import java.util.Optional;

public class UpdateNoteUseCase implements UpdateNoteInport {

    private final NoteOutputPort noteOutputPort;

    private final NoteMapper noteMapper;

    public UpdateNoteUseCase(NoteOutputPort noteInputPort, NoteMapper noteMapper) {
        this.noteOutputPort = noteInputPort;
        this.noteMapper = noteMapper;
    }

    @Override
    public Notes findNoteById(long id) throws Exception {
        Optional<Notes> note = this.noteOutputPort.getNoteById(id);
        if(!note.isPresent()) {
            Notes noteFound = note.get();
            throw new NotesNotFoundException(noteFound.getId());
        }
        return note.get();
    }

    @Override
    public Notes updateNotes(Long id, NoteRequest noteRequest) {
        try {
            Notes note = findNoteById(id);
            this.convert(noteRequest, note);
            Notes noteSaved = this.noteOutputPort.updateNote(id, note);
            return noteSaved;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private void convert(NoteRequest noteRequest, Notes notes) {
        notes.setFirstName(noteRequest.firstName());
        notes.setLastName(noteRequest.lastName());
        notes.setNote(noteRequest.note());
    }
}
