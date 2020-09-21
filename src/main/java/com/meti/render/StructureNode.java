package com.meti.render;

import com.meti.content.Content;
import com.meti.content.StringContent;
import com.meti.stack.CallStack;
import com.meti.type.StructureType;
import com.meti.type.Type;
import com.meti.util.Monad;
import com.meti.util.TriFunction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StructureNode implements Node {
    private final Content name;
    private final Set<Field> fields;

    @Override
    public CallStack define(CallStack stack) {
        return stack.define(name.asString(), fields);
    }

    public StructureNode(Content name, Set<Field> fields) {
        this.name = name;
        this.fields = fields;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public boolean isParent() {
        return false;
    }

    @Override
    public Monad<NodeGroup> group() {
        return new Monad<>(NodeGroup.Structure);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.of(createIdentity());
    }

    private Field createIdentity() {
        return name.value().apply(this::createIdentity);
    }

    private InlineField createIdentity(String name) {
        return new InlineField(name, new StructureType(this.name, fields), Collections.emptyList());
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.empty();
    }

    @Override
    public Prototype createPrototype() {
        return new StructureNodePrototype();
    }

    @Override
    public Optional<String> render() {
        return Optional.of("struct " + name + fields.stream()
                .map(Renderable::render)
                .map(Optional::orElseThrow)
                .map(value -> value + ";")
                .collect(Collectors.joining("", "{", "}")) + ";");
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

    private static class StructureNodePrototype implements Prototype {
        private final Field identity;

        private StructureNodePrototype() {
            this(null);
        }

        private StructureNodePrototype(Field identity) {
            this.identity = identity;
        }

        @Override
        public Prototype withField(Field field) {
            return new StructureNodePrototype(field);
        }

        @Override
        public Prototype withChild(Node child) {
            return this;
        }

        @Override
        public Node build() {
            if (identity == null) throw new IllegalStateException("Identity is null.");
            return identity.destroy().apply((TriFunction<String, Type, List<FieldFlag>, Node>) (s, type, fieldFlags) -> getStructureNode(s, type));
        }

        private StructureNode getStructureNode(String s, Type type) {
            Set<Field> fields = type.streamFields().collect(Collectors.toSet());
            return new StructureNode(new StringContent(s), fields);
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
