package com.meti;

import java.util.List;
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
    public Content sliceToEnd(int start) {
        int length = value.length();
        return slice(start, length);
    }

    //TODO: deprecate this method because String.split doesn't return content and its indices
    @Override
    public Stream<Content> split(String regex){
        return List.of(value.split(regex))
                .stream()
                .map(child -> new ChildContent(this, child, -1, -1));
    }

    @Override
    public boolean isEmpty() {
        return value.isBlank();
    }

    @Override
    public OptionalInt indexFrom(String sequence, int end){
        int index = value.indexOf(sequence, end);
        if(index == -1) return OptionalInt.empty();
        return OptionalInt.of(index);
    }

    @Override
    public boolean isPresent() {
        return !value.isBlank();
    }
}
