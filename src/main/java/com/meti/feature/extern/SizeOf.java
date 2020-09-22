package com.meti.feature.extern;

import com.meti.content.Content;
import com.meti.feature.render.Field;
import com.meti.feature.render.Leaf;
import com.meti.feature.render.Node;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class SizeOf extends Leaf {
    private final Field identity;

    public SizeOf(Field identity) {
        this.identity = identity;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.SizeOf);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.of(identity);
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl();
    }

    @Override
    public Optional<String> renderOptionally() {
        return Optional.of("sizeof(" + identity.renderOptionally().orElseThrow(() -> new IllegalStateException("Cannot renderOptionally identity: " + identity)) + ")");
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
            if(identity == null) throw new IllegalStateException("No identity was provided.");
            return new SizeOf(identity);
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
