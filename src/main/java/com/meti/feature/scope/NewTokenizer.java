package com.meti.feature.scope;

import com.meti.content.Content;
import com.meti.content.StringContent;
import com.meti.feature.evaluate.tokenize.AbstractNodeTokenizer;
import com.meti.feature.render.Field;
import com.meti.feature.render.Node;
import com.meti.feature.block.invoke.Mapping;
import com.meti.feature.extern.SizeOf;
import com.meti.feature.render.ContentType;
import com.meti.feature.scope.variable.Variable;

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
