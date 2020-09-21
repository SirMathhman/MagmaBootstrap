package com.meti.render;

import java.util.stream.Stream;

public interface UntypedNode extends Node {
    @Override
    default Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    default Prototype create(Node child){
        return createPrototype().withChild(child);
    }

    @Override
    default Prototype create(Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Prototype createWithChildren() {
        return streamChildren()
                .map(this::create)
                .reduce(createPrototype(), Prototype::merge);
    }
}
