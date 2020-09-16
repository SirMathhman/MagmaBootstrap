package com.meti.stack;

import com.meti.render.Field;
import com.meti.type.Type;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ImmutableFrame implements Frame {
    private final List<Field> fields;

    ImmutableFrame() {
        this(Collections.emptyList());
    }


    ImmutableFrame(List<Field> fields) {
        this.fields = new ArrayList<>(fields);
    }

    @Override
    public String toString() {
        List<String> names = fields.stream().reduce(new ArrayList<>(), this::merge, (oldList, newList) -> newList);
        String joinedNames = String.join(",", names);
        return String.format("[%s]", joinedNames);
    }

    private List<String> merge(List<String> strings, Field field) {
        field.applyToName(strings::add);
        return strings;
    }

    @Override
    public Frame define(Field field) {
        fields.add(field);
        return new ImmutableFrame(fields);
    }

    @Override
    public boolean isDefined(String name) {
        return fields.stream().anyMatch(field -> isNamed(field, name));
    }

    @Override
    public Optional<Monad<Type>> resolve(String name) {
        return fields.stream()
                .filter(field -> isNamed(field, name))
                .map(Field::type)
                .findFirst();
    }

    private boolean isNamed(Field field, String name) {
        return field.applyToName(name::equals);
    }
}