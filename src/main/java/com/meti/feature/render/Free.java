package com.meti.feature.render;

import com.meti.content.Content;

import java.util.Optional;
import java.util.function.Function;

public abstract class Free extends Parent {
    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

}
