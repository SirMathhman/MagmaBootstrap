package com.meti.render;

public abstract class PassPrototype implements Node.Prototype {
    @Override
    public Node.Prototype withField(Field field) {
        return this;
    }

    @Override
    public Node.Prototype withChild(Node child) {
        return this;
    }
}
