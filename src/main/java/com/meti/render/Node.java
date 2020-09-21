package com.meti.render;

import com.meti.content.Content;
import com.meti.stack.CallStack;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Node extends Renderable {
    Prototype create(Field field);

    Prototype create(Node child);

    default CallStack define(CallStack stack) {
        throw new UnsupportedOperationException();
    }

    <R> Optional<R> applyToContent(Function<Content, R> function);

    boolean isParent();

    Monad<NodeGroup> group();

    Stream<Field> streamFields();

    Stream<Node> streamChildren();

    Prototype createPrototype();

    Prototype createWithChildren();

    default Prototype transformByIdentity(Function<Field, Field> function) {
        Field oldIdentity = streamFields().findFirst().orElseThrow();
        Field newIdentity = function.apply(oldIdentity);
        return create(newIdentity);
    }

    interface Prototype {
        Prototype withField(Field field);

        Prototype withChild(Node child);

        Node build();

        List<Node> listChildren();

        List<Field> listFields();

        Prototype merge(Prototype other);
    }
}
