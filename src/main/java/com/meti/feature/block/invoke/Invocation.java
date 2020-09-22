package com.meti.feature.block.invoke;

import com.meti.content.Content;
import com.meti.feature.render.Field;
import com.meti.feature.render.Node;
import com.meti.feature.render.Parent;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Invocation extends Parent {
    protected final Node caller;
    protected final List<Node> arguments;

    public Invocation(Node caller, List<Node> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    protected abstract String createExtension();

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.concat(Stream.of(caller), arguments.stream());
    }

    @Override
    public Optional<String> renderOptionally() {
        return Optional.of(renderCompletely());
    }

    private String renderCompletely() {
        return renderCaller() + renderArguments() + createExtension();
    }

    private String renderArguments() {
        return arguments.stream()
                .map(Node::renderOptionally)
                .map(Optional::orElseThrow)
                .collect(Collectors.joining(",", "(", ")"));
    }

    private String renderCaller() {
        return caller.renderOptionally().orElseThrow();
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
