package com.meti.feature.block.invoke;

import com.meti.feature.render.Field;
import com.meti.feature.render.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class InvocationPrototype implements Node.Prototype {
    protected final List<Node> children;

    public InvocationPrototype(List<Node> children) {
        this.children = children;
    }

    @Override
    public Node.Prototype withField(Field field) {
        return this;
    }

    @Override
    public Node.Prototype withChild(Node child) {
        List<Node> newChildren = new ArrayList<>(children);
        newChildren.add(child);
        return copy(newChildren);
    }

    protected abstract Node.Prototype copy(List<Node> newChildren);

    @Override
    public Node build() {
        if (children.isEmpty()) throw new IllegalStateException("No caller was provided.");
        Node caller = children.get(0);
        List<Node> arguments = children.subList(1, children.size());
        return build(caller, arguments);
    }

    protected abstract Node build(Node caller, List<Node> arguments);

    @Override
    public List<Node> listChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Field> listFields() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node.Prototype merge(Node.Prototype other) {
        throw new UnsupportedOperationException();
    }
}
