package com.meti.feature.render;

import java.util.function.Function;

public abstract class Parent implements Node {
    @Override
    public boolean isParent() {
        return true;
    }

}
