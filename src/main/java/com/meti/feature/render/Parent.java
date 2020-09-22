package com.meti.feature.render;

import java.util.function.Function;

public abstract class Parent implements Node {
    @Override
    public boolean isParent() {
        return true;
    }

    @Override
    public Node transformFields(Function<Field, Field> mapping) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node transformChildren(Function<Node, Node> mapping) {
        throw new UnsupportedOperationException();
    }
}
