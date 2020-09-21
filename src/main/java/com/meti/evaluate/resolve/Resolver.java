package com.meti.evaluate.resolve;

import com.meti.feature.Type;
import com.meti.util.Monad;

import java.util.Optional;

public interface Resolver {
    Optional<Monad<Type>> resolve();
}
