package com.meti.feature.type.primitive;

import com.meti.content.Content;
import com.meti.feature.render.Leaf;
import com.meti.feature.render.Node;
import com.meti.feature.render.PassPrototype;
import com.meti.feature.scope.UntypedNode;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;

public class String_ extends Leaf implements UntypedNode {
    private final String value;

    public String_(String value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.String);
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl();
    }

    @Override
    public Optional<String> renderOptionally() {
        return Optional.of("\"" + value + "\"");
    }

    private class PrototypeImpl extends PassPrototype {
        @Override
        public Node build() {
            return new String_(value);
        }
    }
}
