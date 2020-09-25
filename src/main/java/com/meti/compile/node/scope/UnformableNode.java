package com.meti.compile.node.scope;

import com.meti.compile.Content;
import com.meti.compile.node.Node;
import com.meti.compile.tokenize.UnformableException;

import java.util.function.Function;

public interface UnformableNode extends Node {
    @Override
    default Node form(Function<Content, Node> former) {
        var message = String.format("Cannot form node of type %s", getClass());
        throw new UnformableException(message);
    }
}
