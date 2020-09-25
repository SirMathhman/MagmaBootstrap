package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public interface Node extends Token {
    Node transformChildren(Function<Node, Node> mapping);

    Node form(Function<Content, Node> former);

    boolean is(Group group);

    enum Group {
        Variable, Content
    }
}
