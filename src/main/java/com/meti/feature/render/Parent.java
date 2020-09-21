package com.meti.feature.render;

import com.meti.feature.render.Node;

public abstract class Parent implements Node {
    @Override
    public boolean isParent() {
        return true;
    }
}
