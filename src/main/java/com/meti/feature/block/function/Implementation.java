package com.meti.feature.block.function;

import com.meti.content.Content;
import com.meti.feature.render.Field;
import com.meti.feature.render.InlineField;
import com.meti.feature.render.Node;
import com.meti.feature.render.Parent;
import com.meti.feature.scope.Type;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Implementation extends Parent {
    private final Type returnType;
    private final String name;
    private final List<Field> parameters;
    private final Node value;

    public Implementation(String name, List<Field> parameters, Type returnType, Node value) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Prototype createPrototype() {
        return new Builder();
    }

    @Override
    public Optional<String> renderOptionally() {
        String renderedParameters = renderParameters();
        return Optional.of(returnType.render(name + renderedParameters) + value.renderOptionally().orElseThrow());
    }

    private String renderParameters() {
        return parameters.stream()
                .map(Field::renderOptionally)
                .flatMap(Optional::stream)
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public Stream<Field> streamFields() {
        List<Field> list = new ArrayList<>();
        list.add(new InlineField(name, returnType, Collections.emptyList()));
        list.addAll(parameters);
        return list.stream();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.of(value);
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Implementation);
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

    public static class Builder implements Prototype {
        private final List<Field> fields;
        private final Node value;

        public Builder() {
            this(Collections.emptyList(), null);
        }

        public Builder withIdentity(Field identity) {
            if (fields.isEmpty()) fields.add(identity);
            else fields.set(0, identity);
            return this;
        }

        public Builder withParameters(List<Field> parameters){
            this.fields.addAll(parameters);
            return this;
        }

        public Builder(List<Field> fields, Node value) {
            this.fields = new ArrayList<>(fields);
            this.value = value;
        }

        @Override
        public Prototype withField(Field field) {
            List<Field> newFields = new ArrayList<>(this.fields);
            newFields.add(field);
            return new Builder(newFields, value);
        }

        @Override
        public Prototype withChild(Node child) {
            return new Builder(fields, child);
        }

        @Override
        public Node build() {
            if (fields.isEmpty()) {
                throw new IllegalStateException("No return type was provided.");
            } else {
                return fields.get(0).applyDestruction(this::createNode);
            }
        }

        private Implementation createNode(String name, Type returnType) {
            List<Field> parameters = fields.subList(1, fields.size());
            if(value == null) {
                String message = String.format("Concrete function '%s' must have a value.", name);
                throw new IllegalStateException(message);
            }
            return new Implementation(name, parameters, returnType, value);
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
