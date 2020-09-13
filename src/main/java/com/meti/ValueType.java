package com.meti;

import java.util.Optional;
import java.util.function.Function;

@Deprecated
public class ValueType implements Type {
    private final String value;

    @Override
    public <R> Optional<R> applyToValue(Function<String, R> function) {
        return Optional.of(value).map(function);
    }

    public ValueType(String value) {
        this.value = value;
    }

    @Override
    public String render(String name) {
        throw new UnsupportedOperationException("Cannot render ValueType");
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }
}
