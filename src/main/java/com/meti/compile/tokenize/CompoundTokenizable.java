package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class CompoundTokenizable<T extends Token> extends AbstractTokenizable<T> {
    public CompoundTokenizable(Content content) {
        super(content);
    }

    protected abstract Stream<TokenizableFactory<T>> streamFactories();

    @Override
    public Optional<T> tokenize() {
        return streamFactories().map(factory -> factory.create(content))
                .map(Tokenizable::tokenize)
                .flatMap(Optional::stream)
                .findFirst();
    }
}
