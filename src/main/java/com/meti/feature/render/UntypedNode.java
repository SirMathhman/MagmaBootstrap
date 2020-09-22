package com.meti.feature.render;

import java.util.function.Function;
import java.util.stream.Stream;

public interface UntypedNode extends Node {
    @Override
    default Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    default Node transformFields(Function<Field, Field> mapping) {
        return this;
    }
}
