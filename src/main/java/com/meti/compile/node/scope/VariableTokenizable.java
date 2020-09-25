package com.meti.compile.node.scope;

import com.meti.compile.Content;
import com.meti.compile.node.Node;
import com.meti.compile.tokenize.AbstractTokenizable;

import java.util.Optional;

public class VariableTokenizable extends AbstractTokenizable<Node> {
    public VariableTokenizable(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        return Optional.of(new Variable(content.asString()));
    }
}
