package com.meti.compile.node.block.function;

import com.meti.compile.Content;
import com.meti.compile.node.ContentNode;
import com.meti.compile.node.Node;
import com.meti.compile.tokenize.AbstractTokenizable;

import java.util.Optional;

public class ReturnTokenizable extends AbstractTokenizable<Node> {
    protected ReturnTokenizable(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> tokenize() {
        if (content.startsWith("return ")) {
            var valueContent = content.sliceToEnd(7);
            var value = new ContentNode(valueContent);
            return Optional.of(new Return(value));
        }
        return Optional.empty();
    }
}
