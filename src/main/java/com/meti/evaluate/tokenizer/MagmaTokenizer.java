package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.evaluate.Evaluator;
import com.meti.render.Node;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaTokenizer extends CollectiveTokenizer {
    public MagmaTokenizer(Content content) {
        super(content);
    }

    @Override
    protected Stream<Function<Content, Evaluator<Node>>> streamFactories() {
        return Stream.of(
                DereferenceTokenizer::new,
                ReferenceTokenizer::new,
                ReturnTokenizer::new,
                InvocationTokenizer::new,
                BlockTokenizer::new,
                FunctionTokenizer::new,
                IntegerTokenizer::new,
                DeclareTokenizer::new,
                VariableTokenizer::new
        );
    }
}
