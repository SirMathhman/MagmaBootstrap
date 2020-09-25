package com.meti.compile.tokenize;

import java.util.Optional;

public interface Tokenizable<T extends Token> {
    Optional<T> tokenize();
}
