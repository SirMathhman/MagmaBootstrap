package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class FunctionNode implements Node {
    private final Type returnType;
    private final String name;
    private final List<Field> parameters;
    private final Node value;

    public FunctionNode(String name, List<Field> parameters, Type returnType, Node value) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createPrototype(){
        return new FunctionBuilder();
    }

    @Override
    public Optional<String> render(){
        String renderedParameters = parameters.stream()
                .map(Field::render)
                .flatMap(Optional::stream)
                .collect(Collectors.joining(",", "(", ")"));
        return Optional.of(returnType.render(name + renderedParameters) + value.render());
    }
}
