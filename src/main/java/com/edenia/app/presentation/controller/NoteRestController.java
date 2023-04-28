package com.edenia.app.presentation.controller;

import com.edenia.app.infrastructure.mapper.NoteMapper;
import com.edenia.app.core.application.ports.input.DeleteNoteInputPort;
import com.edenia.app.core.application.ports.input.FindNoteInputPort;
import com.edenia.app.core.application.ports.input.SaveNoteInputPort;
import com.edenia.app.core.application.ports.input.UpdateNoteInport;
import com.edenia.app.core.application.usecases.CreateNoteUseCase;
import com.edenia.app.core.application.usecases.DeleteNoteUseCase;
import com.edenia.app.core.application.usecases.FindNoteUseCase;
import com.edenia.app.core.application.usecases.UpdateNoteUseCase;
import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.core.domain.request.NoteRequest;
import com.edenia.app.presentation.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteRestController {
    private final NoteService noteService;

    private final SaveNoteInputPort saveNoteInputPort;

    private final UpdateNoteInport updateNoteInport;

    private final FindNoteInputPort findNoteInputPort;

    private final DeleteNoteInputPort deleteNoteInputPort;


    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
        NoteMapper noteMapper = new NoteMapper();
        this.saveNoteInputPort = new CreateNoteUseCase(this.noteService, noteMapper);
        this.updateNoteInport = new UpdateNoteUseCase(this.noteService, noteMapper);
        this.findNoteInputPort = new FindNoteUseCase(this.noteService);
        this.deleteNoteInputPort = new DeleteNoteUseCase(this.noteService);
    }


    @GetMapping("/")
    private ResponseEntity<List<Notes>> fetchNotes() {
        List<Notes> notes = this.findNoteInputPort.fetchNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Notes> fetchNoteById(@PathVariable Long id) throws Exception {
        Optional<Notes> notes = this.findNoteInputPort.fetchNote(id);
        return ResponseEntity.ok(notes.get());
    }


    @PostMapping("/")
    private ResponseEntity<Notes> createNotes(@RequestBody NoteRequest noteRequest) {
        Notes notes = this.saveNoteInputPort.saveNote(noteRequest);
        return ResponseEntity.ok(notes);
    }


    @PutMapping("/{id}")
    private ResponseEntity<Notes> updateNotes(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        Notes notes = this.updateNoteInport.updateNotes(id, noteRequest);
        return ResponseEntity.ok(notes);
    }


    @DeleteMapping("/{id}")
    private void deleteNotes(@PathVariable Long id) {
        this.deleteNoteInputPort.delete(id);
    }


    @GetMapping("hello")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

}
