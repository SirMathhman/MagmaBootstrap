package com.meti.feature.type.point;

import com.meti.content.Content;
import com.meti.feature.AbstractNodeTokenizer;
import com.meti.feature.ContentNode;
import com.meti.feature.Node;

import java.util.Optional;

public class DereferenceTokenizer extends AbstractNodeTokenizer {
    public DereferenceTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("*")) {
            Content valueContent = this.content.sliceToEnd(1);
            Node value = new ContentNode(valueContent);
            return Optional.of(new Dereference(value));
        }
        return Optional.empty();
    }
}
