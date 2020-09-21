package com.meti.feature.render;

import java.util.stream.Stream;

public abstract class Leaf implements Node {
    @Override
    public Stream<Node> streamChildren() {
        return Stream.empty();
    }

    @Override
    public boolean isParent() {
        return false;
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
}
