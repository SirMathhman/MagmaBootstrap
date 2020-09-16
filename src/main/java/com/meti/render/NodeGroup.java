package com.meti.render;

import java.util.function.Predicate;

public enum NodeGroup {
    ConcreteFunction,
    Variable, Block, Return, Integer, Declare, Reference, Dereference, AbstractFunction, Invocation;

    public Predicate<NodeGroup> matches() {
        return (NodeGroup group) -> group == this;
    }
}
