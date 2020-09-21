package com.meti.feature.scope;

import com.meti.content.Content;
import com.meti.feature.AbstractNodeTokenizer;
import com.meti.feature.Node;

import java.util.Optional;

public class VariableTokenizer extends AbstractNodeTokenizer {
    public VariableTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.of(new Variable(content));
    }

}
