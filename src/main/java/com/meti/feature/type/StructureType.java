package com.meti.feature.type;

import com.meti.content.Content;
import com.meti.feature.Field;
import com.meti.feature.Type;
import com.meti.util.Monad;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class StructureType implements Type {
    private final Content name;
    private final Set<Field> fields;

    public StructureType(Content name, Set<Field> fields) {
        this.name = name;
        this.fields = fields;
    }

    public StructureType(Content name) {
        this(name, Collections.emptySet());
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.of(name).map(function);
    }

    private Optional<String> renderOptionally(String name) {
        return this.name.value().map(inner -> "struct " + inner + " " + name).toOption();
    }

    @Override
    public Prototype createPrototype() {
        return new StructureTypePrototype(name);
    }

    @Override
    public Stream<Type> streamChildren() {
        return Stream.empty();
    }

    @Override
    public Stream<Field> streamFields() {
        return fields.stream();
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Structure);
    }

    private Optional<String> renderOptionally() {
        return renderOptionally("");
    }

    @Override
    public String render(String name) {
        return renderOptionally().orElseThrow();
    }

    @Override
    public String render(){
        return render("");
    }

    private static class StructureTypePrototype implements Prototype {
        private final Set<Field> fields;
        private final Content name;

        private StructureTypePrototype(Content name) {
            this(name, new HashSet<>());
        }

        private StructureTypePrototype(Content name, Set<Field> fields) {
            this.name = name;
            this.fields = fields;
        }

        @Override
        public Prototype withChild(Type child) {
            return this;
        }

        @Override
        public Prototype withField(Field field) {
            Set<Field> newFields = new HashSet<>(fields);
            newFields.add(field);
            return new StructureTypePrototype(name, newFields);
        }

        @Override
        public Type build() {
            return new StructureType(name, fields);
        }
    }
}