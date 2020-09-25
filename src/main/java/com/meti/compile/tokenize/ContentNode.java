package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public class ContentNode implements Node {
    private final Content content;

    public ContentNode(Content content) {
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
        var format = "Cannot transform content with value of '%s'. This error probably means that this instance hasn't been parsed yet.";
        var message = String.format(format, content.asString());
        throw new UntransformableException(message);
    }
}
