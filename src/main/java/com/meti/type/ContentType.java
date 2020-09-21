package com.meti.type;

import com.meti.content.Content;
import com.meti.render.Field;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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
    public Prototype createPrototype(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<Type> streamChildren(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<Field> streamFields(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Monad<TypeGroup> group(){
        return new Monad<>(TypeGroup.Content);
    }

    @Override
    public String render(String name) {
        String message = "Cannot render ContentType of value '%s' with name '%s'.";
        String formatted = String.format(message, content, name);
        throw new UnsupportedOperationException(formatted);
    }

    @Override
    public String render(){
        return render("");
    }
}