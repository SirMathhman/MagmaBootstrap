package com.meti;

import java.util.Optional;
import java.util.function.Function;

@Deprecated
public class ValueNode implements Node {
    private final String value;

    public ValueNode(String value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createPrototype() {
        return null;
    }

    @Override
    public Optional<String> render(){
        throw new UnsupportedOperationException();
    }
}
