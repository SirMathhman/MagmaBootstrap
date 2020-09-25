package com.meti.compile.tokenize.node;

import com.meti.compile.RootContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VariableTokenizableTest {

    @Test
    void tokenize() {
        var content = new RootContent("test");
        var tokenizable = new VariableTokenizable(content);
        var node = tokenizable.tokenize().orElseThrow();
        assertTrue(node.is(Node.Group.Variable));
        assertEquals("test", node.render());
    }
}