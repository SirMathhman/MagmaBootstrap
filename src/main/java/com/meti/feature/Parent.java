package com.meti.feature;

import com.meti.feature.Node;

public abstract class Parent implements Node {
    @Override
    public boolean isParent() {
        return true;
    }
}
