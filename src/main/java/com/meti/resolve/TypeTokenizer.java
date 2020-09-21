package com.meti.resolve;

import com.meti.feature.scope.Type;

import java.util.Optional;

public interface TypeTokenizer {
    Optional<Type> tokenize();
}
