package com.meti.feature.evaluate.resolve;

import com.meti.feature.evaluate.Evaluator;
import com.meti.feature.scope.Type;
import com.meti.util.Monad;

import java.util.Optional;

public interface Resolver extends Evaluator<Monad<Type>> {
    @Deprecated
    Optional<Monad<Type>> resolve();

    @Override
    default Optional<Monad<Type>> evaluate() {
        return resolve();
    }
}
