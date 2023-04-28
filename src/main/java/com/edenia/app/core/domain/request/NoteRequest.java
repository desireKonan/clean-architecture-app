package com.edenia.app.core.domain.request;

import java.math.BigDecimal;

public record NoteRequest(String firstName, String lastName, BigDecimal note) {

}
