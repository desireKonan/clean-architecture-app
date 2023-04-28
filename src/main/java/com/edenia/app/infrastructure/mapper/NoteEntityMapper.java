package com.edenia.app.infrastructure.mapper;

import com.edenia.app.core.application.mapper.Mapper;
import com.edenia.app.infrastructure.entity.NoteEntity;
import com.edenia.app.core.domain.model.Notes;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class NoteEntityMapper implements Mapper<Notes, NoteEntity> {
    @Override
    public NoteEntity mapTo(Notes input) {
        return NoteEntity.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .note(input.getNote())
                .createdAt(new Date())
                .build();
    }

    @Override
    public Notes reverseTo(NoteEntity output) {
        return new Notes(
                output.getId(),
                output.getFirstName(),
                output.getLastName(),
                output.getNote(),
                output.getCreatedAt(),
                output.getUpdatedAt()
        );
    }
}
