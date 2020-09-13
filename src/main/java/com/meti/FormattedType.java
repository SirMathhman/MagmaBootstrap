package com.meti;

import java.util.Optional;
import java.util.function.Function;

public abstract class FormattedType implements Type {
    @Override
    public String render(String name) {
        return String.format(createFormat(), name);
    }

    protected abstract String createFormat();

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }
}
