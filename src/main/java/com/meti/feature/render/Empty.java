package com.meti.feature.render;

import com.meti.content.Content;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Empty extends Leaf implements Untyped {
    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Empty);
    }

    @Override
    public Prototype createPrototype() {
        return new EmptyPrototype();
    }

    @Override
    public Optional<String> renderOptionally() {
        return Optional.of("");
    }

    private static class EmptyPrototype implements Prototype {
        @Override
        public Prototype withField(Field field) {
            return this;
        }

        @Override
        public Prototype withChild(Node child) {
            return this;
        }

        @Override
        public Node build() {
            return new Empty();
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
