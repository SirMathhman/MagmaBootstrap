package com.meti;

import java.util.Optional;
import java.util.function.Function;

public class ContentType implements Type {
    private final Content content;

    public ContentType(Content content) {
        this.content = content;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.of(content).map(function);
    }

    @Override
    public String render(String name) {
        /*
        TODO: Reduce lambda by adding in content.value().
        Because it's transformed into a Monad with an apply function(),
        it still adheres to the principle of a single level of abstraction.
        */
        return content.value().apply(value -> value + " " + name);
    }
}