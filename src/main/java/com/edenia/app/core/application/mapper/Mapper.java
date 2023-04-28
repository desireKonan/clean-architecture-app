package com.edenia.app.core.application.mapper;


public interface Mapper<I, O> {
    O mapTo(I input);

    I reverseTo(O output);
}
