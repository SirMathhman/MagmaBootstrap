package com.meti;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CollectiveTokenizer extends AbstractTokenizer {
    public CollectiveTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        return streamFactories()
                .map(factory -> factory.apply(content))
                .map(Tokenizer::tokenize)
                .flatMap(Optional::stream)
                .findFirst();
    }

    protected abstract Stream<Function<Content, Tokenizer<Node>>> streamFactories();
}
