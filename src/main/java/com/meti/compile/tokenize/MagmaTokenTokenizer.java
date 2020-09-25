package com.meti.compile.tokenize;

import com.meti.compile.Content;
import com.meti.compile.node.MagmaNodeTokenizable;
import com.meti.compile.node.Node;

public class MagmaTokenTokenizer implements Tokenizer<Node> {
    private Node assembleNode(Node node) {
        return node.form(this::tokenize);
    }

    @Override
    public Node tokenize(Content content) {
        return new MagmaNodeTokenizable(content)
                .tokenize()
                .orElseThrow()
                .transformChildren(this::assembleNode);
    }
}
