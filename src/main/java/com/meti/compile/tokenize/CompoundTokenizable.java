package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class CompoundTokenizable<T extends Token> implements Tokenizable<T> {
    private final Content value;

    public CompoundTokenizable(Content value) {
        this.value = value;
    }

    protected abstract Stream<TokenizableFactory<T>> streamFactories();

    @Override
    public Optional<T> tokenize() {
        return streamFactories().map(factory -> factory.create(value))
                .map(Tokenizable::tokenize)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
