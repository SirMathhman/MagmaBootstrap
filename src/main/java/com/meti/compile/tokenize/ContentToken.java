package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public class ContentToken implements Node {
    private final Content content;

    public ContentToken(Content content) {
        this.content = content;
    }

    @Override
    public Node form(Function<Content, Node> former) {
        return former.apply(content);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }

    @Override
    public String render() {
        var message = String.format("Content of \"%s\" shouldn't be rendered.", content.asString());
        throw new UnrenderableException(message);
    }

    @Override
    public Node transformChildren(Function<Node, Node> mapping) {
        return this;
    }
}
