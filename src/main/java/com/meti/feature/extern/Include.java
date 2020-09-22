package com.meti.feature.extern;

import com.meti.content.Content;
import com.meti.feature.render.Field;
import com.meti.feature.render.Leaf;
import com.meti.feature.render.Node;
import com.meti.feature.render.UntypedNode;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Include extends Leaf implements UntypedNode {
    private final Content value;

    public Include(Content value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.of(value).map(function);
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Include);
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl();
    }

    @Override
    public Optional<String> renderOptionally() {
        return value.value()
                .map(s -> "#include <" + s + ".h>\n")
                .toOption();
    }

    private class PrototypeImpl implements Prototype {
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
            return new Include(value);
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
