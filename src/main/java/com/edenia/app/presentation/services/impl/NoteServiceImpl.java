package com.edenia.app.presentation.services.impl;

import com.edenia.app.infrastructure.mapper.NoteEntityMapper;
import com.edenia.app.infrastructure.entity.NoteEntity;
import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.infrastructure.repository.NoteJpaRepository;
import com.edenia.app.presentation.services.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteJpaRepository noteJpaRepository;

    private final NoteEntityMapper noteEntityMapper;

    public NoteServiceImpl(NoteJpaRepository noteJpaRepository) {
        this.noteJpaRepository = noteJpaRepository;
        this.noteEntityMapper = new NoteEntityMapper();
    }

    @Override
    public List<Notes> findAllNotes() {
        List<Notes> notes = noteJpaRepository.findAll()
                .stream()
                .map(noteEntityMapper::reverseTo)
                .collect(Collectors.toList());
        return notes;
    }

    @Override
    public Optional<Notes> getNoteById(long id) {
        NoteEntity noteFound = noteJpaRepository.findById(id).get();
        return Optional.of(noteFound).map(noteEntityMapper::reverseTo);
    }

    @Override
    public Notes createNote(Notes noteRequest) {
        NoteEntity noteSaved = noteJpaRepository.save(noteEntityMapper.mapTo(noteRequest));
        return noteEntityMapper.reverseTo(noteSaved);
    }

    @Override
    public Notes updateNote(long id, Notes noteRequest) {
        Optional<NoteEntity> noteFound = noteJpaRepository.findById(id);
        Notes notes = new Notes();
        NoteEntity noteSaved = new NoteEntity();
        if(noteFound.isPresent()) {
            noteSaved = noteFound.get();
            noteSaved.setFirstName(noteRequest.getFirstName());
            noteSaved.setLastName(noteRequest.getLastName());
            noteSaved.setNote(noteRequest.getNote());
            noteSaved.setUpdatedAt(new Date());
        }
        noteSaved = noteJpaRepository.save(noteSaved);
        return noteEntityMapper.reverseTo(noteSaved);
    }

    @Override
    public void deleteNote(long id) {
        noteJpaRepository.deleteById(id);
    }
}
