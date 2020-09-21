package com.meti.type;

import com.meti.content.Content;
import com.meti.render.Field;
import com.meti.render.Renderable;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Type {
    Monad<TypeGroup> group();

    <R> Optional<R> applyToContent(Function<Content, R> function);

    @Deprecated
    Optional<String> renderOptionally(String name);

    String render(String name);

    String render();

    Prototype createPrototype();

    Stream<Type> streamChildren();

    Stream<Field> streamFields();

    @Deprecated
    Optional<String> renderOptionally();

    interface Prototype {
        Prototype withChild(Type child);

        Prototype withField(Field field);

        Type build();
    }
}
