package com.meti.compile.tokenize;

import com.meti.compile.Content;
import com.meti.compile.tokenize.node.MagmaNodeTokenizable;
import com.meti.compile.tokenize.node.Node;

public class MagmaTokenTokenizer implements Tokenizer<Node> {
    private Node assembleNode(Node node) {
        return node.form(this::tokenize);
    }

    private TokenizeException createInvalid(Content content) {
        var message = content.format("Invalid token at line %d, index %d: %s");
        return new TokenizeException(message);
    }

    @Override
    public Node tokenize(Content content) {
        return new MagmaNodeTokenizable(content)
                .tokenize()
                .orElseThrow(() -> createInvalid(content))
                .transformChildren(this::assembleNode);
    }
}
