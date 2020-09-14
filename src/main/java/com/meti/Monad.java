package com.meti;

import java.util.function.Function;

public class Monad<T> {
    private final T value;

    public Monad(T value) {
        this.value = value;
    }

    public <R> R apply(Function<T, R> function) {
        return function.apply(value);
    }
}
