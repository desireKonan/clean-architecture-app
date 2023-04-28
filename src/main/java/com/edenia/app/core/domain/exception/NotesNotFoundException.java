package com.edenia.app.core.domain.exception;

public class NotesNotFoundException extends Exception {

    private final static String NOTES_NOT_FOUND = "";

    public NotesNotFoundException(Long id) {
        super("Note " + id + " non trouvé !");
    }
}
