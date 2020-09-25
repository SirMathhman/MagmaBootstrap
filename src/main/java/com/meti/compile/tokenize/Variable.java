package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public class Variable implements Node {
    private final String value;

    Variable(String value) {
        this.value = value;
    }

    @Override
    public Node form(Function<Content, Node> former) {
        var message = String.format("Cannot form node of type %s", getClass());
        throw new UnformableException(message);
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
