package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.evaluate.Evaluator;
import com.meti.render.Node;

import java.util.function.Function;
import java.util.stream.Stream;

public class RootTokenizer extends CollectiveTokenizer {
    public RootTokenizer(Content content) {
        super(content);
    }

    @Override
    protected Stream<Function<Content, Evaluator<Node>>> streamFactories() {
        return Stream.of(
                ReturnTokenizer::new,
                BlockTokenizer::new,
                FunctionTokenizer::new,
                IntegerTokenizer::new,
                DeclareTokenizer::new,
                VariableTokenizer::new
        );
    }
}
