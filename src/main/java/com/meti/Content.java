package com.meti;

import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Content {
    <R> R applyToValue(Function<String, R> mapper);

    Content slice(int start);

    Content slice(int start, int end);

    OptionalInt index(String sequence);

    Stream<Content> split(String regex);

    boolean isPresent();

    @Deprecated
    boolean isEmpty();

    OptionalInt indexFromEnd(String value);
}
