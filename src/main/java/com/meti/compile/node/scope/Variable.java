package com.meti.compile.node.scope;

import com.meti.compile.node.Node;

import java.util.function.Function;

public class Variable implements UnformableNode {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Variable;
    }

    @Override
    public Node transformChildren(Function<Node, Node> mapping) {
        return this;
    }

    @Override
    public String render() {
        return value;
    }
}
