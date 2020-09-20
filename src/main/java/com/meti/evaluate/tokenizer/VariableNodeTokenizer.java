package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.render.Node;

import java.util.Optional;

public class VariableNodeTokenizer extends AbstractNodeTokenizer {
    public VariableNodeTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        return Optional.of(new Variable(content));
    }

}
