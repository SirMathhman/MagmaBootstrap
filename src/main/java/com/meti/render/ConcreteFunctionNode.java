package com.meti.render;

import com.meti.content.Content;
import com.meti.type.Type;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConcreteFunctionNode extends ParentNode {
    private final Type returnType;
    private final String name;
    private final List<Field> parameters;
    private final Node value;

    public ConcreteFunctionNode(String name, List<Field> parameters, Type returnType, Node value) {
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
    public Prototype createPrototype() {
        return new ConcreteFunctionBuilder();
    }

    @Override
    public Optional<String> render() {
        String renderedParameters = renderParameters();
        return Optional.of(returnType.render(name + renderedParameters) + value.render().orElseThrow());
    }

    private String renderParameters() {
        return parameters.stream()
                .map(Field::render)
                .flatMap(Optional::stream)
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public Stream<Field> streamFields() {
        List<Field> list = new ArrayList<>();
        list.add(new InlineField(name, returnType, Collections.emptyList()));
        list.addAll(parameters);
        return list.stream();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.of(value);
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.Implementation);
    }

    @Override
    public Prototype create(Node child){
        return createPrototype().withChild(child);
    }

    @Override
    public Prototype create(Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createWithChildren() {
        return streamChildren()
                .map(this::create)
                .reduce(createPrototype(), Prototype::merge);
    }
}
