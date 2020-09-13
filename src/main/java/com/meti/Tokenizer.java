package com.meti;

import java.util.Optional;

public interface Tokenizer<T extends Node> {
    @Deprecated
    T tokenize();

    Optional<T> tokenizeOptionally();
}
