package com.meti.render;

import com.meti.content.Content;
import com.meti.stack.CallStack;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Node extends Renderable {
    default CallStack define(CallStack stack){
        throw new UnsupportedOperationException();
    }

    <R> Optional<R> applyToContent(Function<Content, R> function);

    boolean isParent();

    Monad<NodeGroup> group();

    Stream<Field> streamFields();

    Stream<Node> streamChildren();

    Prototype createPrototype();

    interface Prototype {
        Prototype withField(Field field);

        Prototype withChild(Node child);

        Node build();
    }
}
