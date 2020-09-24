package com.meti.feature.render;

import com.meti.stack.CallStack;

public abstract class Parent implements Node {
    @Override
    public boolean isParent() {
        return true;
    }

}
