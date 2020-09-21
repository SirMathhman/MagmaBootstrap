package com.meti.feature.render;

import com.meti.feature.scope.Renderable;
import com.meti.feature.scope.Type;
import com.meti.util.Monad;
import com.meti.util.Triad;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Field extends Renderable {
    Monad<String> name();

    <R> R applyDestruction(BiFunction<String, Type, R> function);

    Triad<String, Type, List<Flag>> destroy();

    <R> R applyToName(Function<String, R> mapping);

    Monad<Type> type();

    Field transformByType(Function<Type, Type> mapping);

    enum Flag {
        CONST,
        LET,
        NATIVE,
        IMPLICIT,
        DEF
    }
}