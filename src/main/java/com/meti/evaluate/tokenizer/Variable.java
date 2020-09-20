package com.meti.evaluate.tokenizer;

import com.meti.content.Content;
import com.meti.render.Field;
import com.meti.render.LeafNode;
import com.meti.render.Node;
import com.meti.render.NodeGroup;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class Variable extends LeafNode {
    private final Content content;

    public Variable(Content content) {
        this.content = content;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.of(content).map(function);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.empty();
    }

    @Override
    public Prototype createPrototype() {
        return new PrototypeImpl(content);
    }

    @Override
    public Optional<String> render() {
        return content.value().toOption();
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.Variable);
    }

    private static class PrototypeImpl implements Prototype {
        private final Content content;

        public PrototypeImpl(Content content) {
            this.content = content;
        }

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
            return new Variable(content);
        }
    }
}
