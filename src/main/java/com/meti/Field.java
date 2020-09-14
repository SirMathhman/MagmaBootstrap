package com.meti;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Field extends Node {
    <R> R applyToType(Function<Type, R> mapping);

    Field copy(Type type);

    @Override
    default <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    default Prototype createPrototype() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Optional<String> render() {
        throw new UnsupportedOperationException();
    }

    default <R> R applyDestruction(BiFunction<String, Type, R> function) {
        throw new UnsupportedOperationException();
    }
}
