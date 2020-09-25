package com.meti.compile.tokenize.node;

import com.meti.compile.Content;
import com.meti.compile.tokenize.Token;

import java.util.function.Function;

public interface Node extends Token {
    Node transformChildren(Function<Node, Node> mapping);

    Node form(Function<Content, Node> former);

    boolean is(Group group);

    enum Group {
        Variable, Content
    }
}
