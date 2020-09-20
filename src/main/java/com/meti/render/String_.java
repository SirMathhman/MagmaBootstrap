package com.meti.render;

import com.meti.content.Content;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;

public class String_ extends LeafNode implements UntypedNode{
    private final String value;

    public String_(String value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.String);
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl();
    }

    @Override
    public Optional<String> render() {
        return Optional.of("\"" + value + "\"");
    }

    private class PrototypeImpl extends PassPrototype {
        @Override
        public Node build() {
            return new String_(value);
        }
    }
}
