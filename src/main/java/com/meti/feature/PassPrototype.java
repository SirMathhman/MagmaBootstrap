package com.meti.feature;

import com.meti.feature.Field;
import com.meti.feature.Node;

import java.util.List;

public abstract class PassPrototype implements Node.Prototype {
    @Override
    public Node.Prototype withField(Field field) {
        return this;
    }

    @Override
    public Node.Prototype withChild(Node child) {
        return this;
    }

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
