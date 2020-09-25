package com.meti.compile.node.block.function;

import com.meti.compile.node.Node;
import com.meti.compile.node.scope.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReturnTest {

    @Test
    void transformChildren() {
        var parent = createNode();
        var postParent = parent.transformChildren(Return::new);
        assertEquals("return return test;;", postParent.render());
    }

    private Node createNode() {
        var value = new Variable("test");
        return new Return(value);
    }

    @Test
    void is() {
        var node = createNode();
        assertTrue(node.is(Node.Group.Return));
    }

    @Test
    void render() {
        var node = createNode();
        assertEquals("return test;", node.render());
    }
}