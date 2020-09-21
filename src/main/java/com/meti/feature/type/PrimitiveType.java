package com.meti.feature.type;

import com.meti.content.Content;
import com.meti.feature.Field;
import com.meti.feature.Type;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public enum PrimitiveType implements Type {
    I8("char"),
    U8("unsigned char"),
    I16("int"),
    U16("unsigned int"),
    I32("long"),
    U32("unsigned long"),
    I64("long long"),
    U64("unsigned long long"),
    F32("float"),
    F64("double"),
    CHAR("char"),
    BOOL("int"),
    VOID("void"),
    ANY("void");

    private final String value;

    PrimitiveType(String value) {
        this.value = value;
    }

    @Override
    public <R> Optional<R> applyToContent(Function<Content, R> function) {
        return Optional.empty();
    }

    private Optional<String> renderOptionally(String name) {
        return Optional.of(value + " " + name);
    }

    @Override
    public Prototype createPrototype() {
        return new PrimitivePrototype();
    }

    @Override
    public Stream<Type> streamChildren() {
        return Stream.empty();
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

    private class PrimitivePrototype implements Prototype {
        @Override
        public Prototype withChild(Type child) {
            return this;
        }

        @Override
        public Prototype withField(Field field) {
            return this;
        }

        @Override
        public Type build() {
            return PrimitiveType.this;
        }
    }
}
