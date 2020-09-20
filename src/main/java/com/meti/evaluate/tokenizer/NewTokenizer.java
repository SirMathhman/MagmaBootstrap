package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.content.StringContent;
import com.meti.render.*;
import com.meti.type.ContentType;
import com.meti.type.Type;

import java.util.Optional;

public class NewTokenizer extends AbstractNodeTokenizer{
    public NewTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("new ")) {
            Content value = this.content.sliceToEnd(4);
            Type type = new ContentType(value);
            Field identity = new TypeField(type);
            return Optional.of(new MappingNode(new Variable(new StringContent("malloc")), new SizeOf(identity)));
        }
        return Optional.empty();
    }
}
