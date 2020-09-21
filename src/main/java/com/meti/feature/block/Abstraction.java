package com.meti.feature.block;

import com.meti.content.Content;
import com.meti.feature.Field;
import com.meti.feature.InlineField;
import com.meti.feature.Node;
import com.meti.feature.Parent;
import com.meti.feature.Type;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Abstraction extends Parent {
    private final Type returnType;
    private final String name;
    private final List<Field> parameters;
    private final List<Field.Flag> flags;

    public Abstraction(String name, List<Field> parameters, Type returnType, List<Field.Flag> flags) {
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
        this.flags = flags;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    @Override
    public Prototype createPrototype() {
        return new Builder();
    }

    @Override
    public Optional<String> render() {
        if (flags.contains(Field.Flag.NATIVE)) {
            return Optional.of("");
        } else {
            String renderedParameters = renderParameters();
            return Optional.ofNullable(returnType.render(name + renderedParameters));
        }
    }

    private String renderParameters() {
        return parameters.stream()
                .map(Field::render)
                .flatMap(Optional::stream)
                .collect(Collectors.joining(",", "(", ")"));
    }

    @Override
    public Stream<Field> streamFields() {
        List<Field> list = new ArrayList<>();
        list.add(new InlineField(name, returnType, flags));
        list.addAll(parameters);
        return list.stream();
    }

    @Override
    public Stream<Node> streamChildren() {
        return Stream.empty();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Abstraction);
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

        public Builder() {
            this(Collections.emptyList());
        }

        public Builder(List<Field> fields) {
            this.fields = new ArrayList<>(fields);
        }

        public Builder withIdentity(Field identity) {
            if (fields.isEmpty()) fields.add(identity);
            else fields.set(0, identity);
            return this;
        }

        public Builder withParameters(List<Field> parameters) {
            this.fields.addAll(parameters);
            return this;
        }

        @Override
        public Prototype withField(Field field) {
            List<Field> newFields = new ArrayList<>(this.fields);
            newFields.add(field);
            return new Builder(newFields);
        }

        @Override
        public Prototype withChild(Node child) {
            return new Builder(fields);
        }

        @Override
        public Node build() {
            if (fields.isEmpty()) {
                throw new IllegalStateException("No return type was provided.");
            } else {
                return fields.get(0).destroy().apply(this::assemble);
            }
        }

        private Abstraction assemble(String name, Type returnType, List<Field.Flag> flags) {
            List<Field> parameters = fields.subList(1, fields.size());
            return new Abstraction(name, parameters, returnType, flags);
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
