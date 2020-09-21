package com.meti.feature.type;

import com.meti.content.Content;
import com.meti.feature.Node;
import com.meti.feature.Parent;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Field extends Parent {
    private final Content fieldName;
    private final Node parent;

    public Field(Node parent, Content fieldName) {
        this.fieldName = fieldName;
        this.parent = parent;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.of(fieldName).map(function);
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Field);
    }

    @Override
    public Stream<com.meti.feature.Field> streamFields() {
        return Stream.empty();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.of(parent);
    }

    @Override
    public Prototype createPrototype() {
        return new FieldNodePrototype(fieldName, parent);
    }

    @Override
    public Optional<String> render() {
        return Optional.of(parent.render().orElseThrow() + "." + fieldName.value().apply(Function.identity()));
    }

    @Override
    public Prototype create(Node child){
        return createPrototype().withChild(child);
    }

    @Override
    public Prototype create(com.meti.feature.Field field) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createWithChildren() {
        return streamChildren()
                .map(this::create)
                .reduce(createPrototype(), Prototype::merge);
    }

    private static class FieldNodePrototype implements Prototype {
        private final Content fieldName;
        private final Node parent;

        private FieldNodePrototype(Content fieldName) {
            this(fieldName, null);
        }

        private FieldNodePrototype(Content fieldName, Node parent) {
            this.fieldName = fieldName;
            this.parent = parent;
        }

        @Override
        public Prototype withField(com.meti.feature.Field field) {
            return this;
        }

        @Override
        public Prototype withChild(Node child) {
            return new FieldNodePrototype(fieldName, child);
        }

        @Override
        public Node build() {
            return new Field(parent, fieldName);
        }

        @Override
        public List<Node> listChildren() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<com.meti.feature.Field> listFields() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Prototype merge(Prototype other) {
            throw new UnsupportedOperationException();
        }
    }
}
