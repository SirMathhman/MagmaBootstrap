package com.meti.feature.render;

import com.meti.content.Content;

import java.util.Optional;
import java.util.function.Function;

public abstract class Free extends Parent {
    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Prototype create(Node child){
        return createPrototype().withChild(child);
    }

    @Override
    public Prototype create(Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createWithChildren() {
        return streamChildren()
                .map(this::create)
                .reduce(createPrototype(), Prototype::merge);
    }

    @Override
    public Node transformFields(Function<Field, Field> mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node transformChildren(Function<Node, Node> mapping) {
        throw new UnsupportedOperationException();
    }
}
