package com.meti;

import java.util.Optional;

public interface Tokenizer<T extends Node> {
    Optional<T> tokenize();
}
