package com.meti.resolve;

import com.meti.content.Content;
import com.meti.feature.Field;
import com.meti.feature.Type;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class PointerType implements Type {
    private final Type child;

    PointerType(Type child) {
        this.child = child;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    private Optional<String> renderOptionally(String name) {
        return Optional.of(child.render("* " + name));
    }

    @Override
    public Prototype createPrototype() {
        return new PointerPrototype(child);
    }

    @Override
    public Stream<Type> streamChildren() {
        return Stream.of(child);
    }

    @Override
    public Stream<Field> streamFields() {
        return Stream.empty();
    }

    @Override
    public Monad<Group> group(){
        throw new UnsupportedOperationException();
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
}
