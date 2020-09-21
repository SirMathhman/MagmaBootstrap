package com.meti.feature.scope;

import com.meti.content.Content;
import com.meti.content.StringContent;
import com.meti.feature.AbstractNodeTokenizer;
import com.meti.feature.Field;
import com.meti.feature.Node;
import com.meti.feature.TypeField;
import com.meti.feature.block.invoke.Mapping;
import com.meti.feature.extern.SizeOf;
import com.meti.feature.ContentType;
import com.meti.feature.Type;

import java.util.Optional;

public class NewTokenizer extends AbstractNodeTokenizer {
    public NewTokenizer(Content content) {
        super(content);
    }

    @Override
    public Optional<Node> evaluate() {
        if(content.startsWith("new ")) {
            Content value = this.content.sliceToEnd(4);
            Type type = new ContentType(value);
            Field identity = new TypeField(type);
            return Optional.of(new Mapping(new Variable(new StringContent("malloc")), new SizeOf(identity)));
        }
        return Optional.empty();
    }
}
