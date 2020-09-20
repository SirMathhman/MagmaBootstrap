package com.meti.render;

import com.meti.content.Content;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class SizeOf extends LeafNode {
    private final Field identity;

    public SizeOf(Field identity) {
        this.identity = identity;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.SizeOf);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.of(identity);
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl(identity);
    }

    @Override
    public Optional<String> render() {
        return Optional.of("sizeof(" + identity.render().orElseThrow() + ")");
    }

    private static class PrototypeImpl implements Prototype {
        private final Field identity;

        private PrototypeImpl() {
            this(null);
        }

        private PrototypeImpl(Field identity) {
            this.identity = identity;
        }

        @Override
        public Prototype withField(Field field) {
            return new PrototypeImpl(field);
        }

        @Override
        public Prototype withChild(Node child) {
            return this;
        }

        @Override
        public Node build() {
            return new SizeOf(identity);
        }
    }
}
