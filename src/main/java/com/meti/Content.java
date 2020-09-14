package com.meti;

import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Content {
    //TODO: return monad
    <R> R applyToValue(Function<String, R> mapper);

    Content sliceToEnd(int start);

    Content slice(int start, int end);

    OptionalInt index(String sequence);

    @Deprecated
    Stream<Content> split(String regex);

    default Stream<Content> splitByStrategy(Strategy strategy) {
        throw new UnsupportedOperationException();
    }

    boolean isPresent();

    @Deprecated
    boolean isEmpty();

    OptionalInt indexFrom(String sequence, int end);
}
