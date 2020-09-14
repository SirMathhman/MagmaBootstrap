package com.meti;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Node extends Renderable {
    <R> Optional<R> applyToContent(Function<Content, R> function);

    default Stream<Field> streamFields() {
        return Stream.empty();
    }

    default Stream<Node> streamChildren() {
        return Stream.empty();
    }

    Prototype createPrototype();

    interface Prototype {
        Prototype withField(Field field);

        Prototype withChild(Node child);

        Node build();
    }
}
