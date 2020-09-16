package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.render.ContentNode;
import com.meti.render.DereferenceNode;
import com.meti.render.Node;

import java.util.Optional;

public class DereferenceNodeTokenizer extends AbstractNodeTokenizer {
    public DereferenceNodeTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("*")) {
            Content valueContent = this.content.sliceToEnd(1);
            Node value = new ContentNode(valueContent);
            return Optional.of(new DereferenceNode(value));
        }
        return Optional.empty();
    }
}