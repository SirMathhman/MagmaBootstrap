package com.meti;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

public class RootContent implements Content {
    private final String value;

    public RootContent(String value) {
        this.value = value;
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
    public OptionalInt index(String sequence) {
        int index = value.indexOf(sequence);
        if(index == -1) return OptionalInt.empty();
        else return OptionalInt.of(index);
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
