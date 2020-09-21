package com.meti.render;

import com.meti.type.Type;
import com.meti.util.Monad;
import com.meti.util.Triad;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TypeField implements Field{
    private final Type type;

    public TypeField(Type type) {
        this.type = type;
    }

    @Override
    public Monad<String> name() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <R> R applyToType(Function<Type, R> mapping) {
        return mapping.apply(type);
    }

    @Override
    public Field copy(Type type) {
        return new TypeField(type);
    }

    @Override
    public <R> R applyDestruction(BiFunction<String, Type, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Triad<String, Type, List<FieldFlag>> destroy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <R> R applyToName(Function<String, R> mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Monad<Type> type() {
        return new Monad<>(type);
    }

    @Override
    public Optional<String> render() {
        return Optional.ofNullable(type.renderOptionally().orElseThrow(() -> new IllegalStateException("Cannot renderOptionally type: " + type)));
    }
}
