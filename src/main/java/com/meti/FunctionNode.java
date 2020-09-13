package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class FunctionNode implements Node {
    private final Type type;
    private final String name;
    private final List<Field> fields;
    private final Node value;

    public FunctionNode(String name, List<Field> fields, Type type, Node value) {
        this.type = type;
        this.name = name;
        this.fields = fields;
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createPrototype(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<String> render(){
        throw new UnsupportedOperationException();
    }
}
