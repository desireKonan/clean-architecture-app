package com.edenia.app.infrastructure.mapper;

import com.edenia.app.core.application.mapper.Mapper;
import com.edenia.app.core.domain.model.Notes;
import com.edenia.app.core.domain.request.NoteRequest;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper implements Mapper<NoteRequest, Notes> {
    @Override
    public Notes mapTo(NoteRequest input) {
        Notes output = new Notes();
        output.setFirstName(input.firstName());
        output.setLastName(input.lastName());
        output.setNote(input.note());
        return output;
    }

    @Override
    public NoteRequest reverseTo(Notes output) {
        return null;
    }
}
