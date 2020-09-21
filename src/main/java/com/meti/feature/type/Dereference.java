package com.meti.feature.type;

import com.meti.content.Content;
import com.meti.feature.Field;
import com.meti.feature.Node;
import com.meti.feature.Parent;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Dereference extends Parent {
    private final Node value;

    public Dereference(Node value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Dereference);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.of(value);
    }

    @Override
    public Prototype createPrototype() {
        return new DereferencePrototype();
    }

    @Override
    public Optional<String> render() {
        return Optional.of("*" + value.render().orElseThrow());
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

    private static class DereferencePrototype implements Prototype {
        private final Node value;

        private DereferencePrototype() {
            this(null);
        }

        private DereferencePrototype(Node value) {
            this.value = value;
        }

        @Override
        public Prototype withField(Field field) {
            return this;
        }

        @Override
        public Prototype withChild(Node child) {
            return new DereferencePrototype(child);
        }

        @Override
        public Node build() {
            if(value == null) throw new IllegalStateException("No value was provided.");
            return new Dereference(value);
        }

        @Override
        public List<Node> listChildren() {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<Field> listFields() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Prototype merge(Prototype other) {
            throw new UnsupportedOperationException();
        }
    }
}
