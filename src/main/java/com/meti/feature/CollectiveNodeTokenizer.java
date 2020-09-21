package com.meti.feature;

import com.meti.content.Content;
import com.meti.evaluate.Evaluator;
import com.meti.feature.AbstractNodeTokenizer;
import com.meti.feature.Node;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CollectiveNodeTokenizer extends AbstractNodeTokenizer {
    public CollectiveNodeTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return streamFactories()
                .map(factory -> factory.apply(content))
                .map(Evaluator::evaluate)
                .flatMap(Optional::stream)
                .findFirst();
    }

    protected abstract Stream<Function<Content, Evaluator<Node>>> streamFactories();
}
