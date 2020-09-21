package com.meti.feature.type;

import com.meti.content.Content;
import com.meti.feature.AbstractNodeTokenizer;
import com.meti.feature.ContentNode;
import com.meti.feature.Node;

import java.util.Optional;
import java.util.OptionalInt;

public class FieldTokenizer extends AbstractNodeTokenizer {
    public FieldTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        OptionalInt separatorOptional = content.index(".");
        if (separatorOptional.isPresent()) {
            int separator = separatorOptional.getAsInt();
            Content parentContent = content.slice(0, separator);
            Node parent = new ContentNode(parentContent);
            Content fieldName = content.sliceToEnd(separator + 1);
            return Optional.of(new Field(parent, fieldName));
        }
        return Optional.empty();
    }
}
