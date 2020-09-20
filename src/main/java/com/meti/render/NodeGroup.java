package com.meti.render;

import java.util.function.Predicate;

public enum NodeGroup {
    Implementation,
    Variable, Block, Return, Integer, Declare, Reference, Dereference, Abstraction, Mapping, Procedure, Structure, Construction, Field, Cast, Include, Empty, Import, SizeOf, String;

    public Predicate<NodeGroup> matches() {
        return (NodeGroup group) -> group == this;
    }

    public boolean matches(Node node) {
        return node.group().test(matches());
    }
}
