package com.meti.render;

public abstract class LeafNode implements Node {
    @Override
    public boolean isParent() {
        return false;
    }
}
