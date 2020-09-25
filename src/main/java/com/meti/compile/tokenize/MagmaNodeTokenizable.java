package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.stream.Stream;

public class MagmaNodeTokenizable extends CompoundTokenizable<Node> {
    public MagmaNodeTokenizable(Content value) {
        super(value);
    }

    @Override
    protected Stream<TokenizableFactory<Node>> streamFactories() {
        return Stream.of();
    }
}
