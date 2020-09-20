package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.render.IncludeNode;
import com.meti.render.Node;

import java.util.Optional;

public class ImportTokenizer extends AbstractNodeTokenizer{
    public ImportTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("import native")) {
            Content value = this.content.sliceToEnd(13);
            return Optional.of(new IncludeNode(value));
        }
        return Optional.empty();
    }
}
