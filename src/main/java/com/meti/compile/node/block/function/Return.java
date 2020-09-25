package com.meti.compile.node.block.function;

import com.meti.compile.node.Node;
import com.meti.compile.node.scope.UnformableNode;

import java.util.function.Function;

public class Return implements UnformableNode {
    private final Node value;

    public Return(Node value) {
        this.value = value;
    }

    @Override
    public Node transformChildren(Function<Node, Node> mapping) {
        var newValue = mapping.apply(value);
        return new Return(newValue);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Return;
    }

    @Override
    public String render() {
        var renderedValue = value.render();
        return String.format("return %s;", renderedValue);
    }
}
