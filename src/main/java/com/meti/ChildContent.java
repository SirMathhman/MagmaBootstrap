package com.meti;

import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChildContent implements Content {
    private final Content parent;
    private final String value;
    private final int start;
    private final int end;

    public ChildContent(Content parent, String value, int from, int end) {
        this.parent = parent;
        this.value = value;
        this.start = from;
        this.end = end;
    }

    @Override
    public <R> R applyToValue(Function<String, R> mapper) {
        return mapper.apply(value);
    }

    @Override
    public Content slice(int start, int end) {
        String child = value.substring(start, end);
        String formatted = child.trim();
        return new ChildContent(this, formatted, start, end);
    }

    @Override
    public OptionalInt index(String sequence){
        throw new UnsupportedOperationException();
    }

    @Override
    public Content slice(int start) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<Content> split(String regex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return value.isBlank();
    }

    @Override
    public OptionalInt indexFromEnd(String value){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPresent() {
        throw new UnsupportedOperationException();
    }
}
