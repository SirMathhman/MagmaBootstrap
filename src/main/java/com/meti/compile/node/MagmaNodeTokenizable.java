package com.meti.compile.node;

import com.meti.compile.Content;
import com.meti.compile.node.scope.VariableTokenizable;
import com.meti.compile.tokenize.CompoundTokenizable;
import com.meti.compile.tokenize.TokenizableFactory;

import java.util.stream.Stream;

public class MagmaNodeTokenizable extends CompoundTokenizable<Node> {
    public MagmaNodeTokenizable(Content value) {
        super(value);
    }

    @Override
    protected Stream<TokenizableFactory<Node>> streamFactories() {
        return Stream.of(VariableTokenizable::new);
    }
}
