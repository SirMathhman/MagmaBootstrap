package com.meti.render;

import com.meti.content.Content;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvocationNode extends ParentNode{
    private final Node caller;
    private final List<Node> arguments;

    public InvocationNode(Node caller, List<Node> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.Invocation);
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
    public Prototype createPrototype() {
        return new InvocationPrototype();
    }

    @Override
    public Optional<String> render() {
        return Optional.of(caller.render().orElseThrow() + arguments.stream()
                .map(Node::render)
                .map(Optional::orElseThrow)
                .collect(Collectors.joining(",", "(", ")")));
    }

    private static class InvocationPrototype implements Prototype {
        private final List<Node> children;

        private InvocationPrototype() {
            this(new ArrayList<>());
        }

        private InvocationPrototype(List<Node> children) {
            this.children = children;
        }

        @Override
        public Prototype withField(Field field) {
            return this;
        }

        @Override
        public Prototype withChild(Node child) {
            List<Node> newChildren = new ArrayList<>(children);
            newChildren.add(child);
            return new InvocationPrototype(newChildren);
        }

        @Override
        public Node build() {
            if(children.isEmpty()) throw new IllegalStateException("No caller was provided.");
            Node caller = children.get(0);
            List<Node> arguments = children.subList(1, children.size());
            return new InvocationNode(caller, arguments);
        }
    }
}
