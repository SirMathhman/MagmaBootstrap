package com.meti.compile.node.scope;

import com.meti.compile.RootContent;
import com.meti.compile.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VariableTokenizableTest {

    @Test
    void tokenize() {
        var content = new RootContent("test");
        var tokenizable = new VariableTokenizable(content);
        var node = tokenizable.tokenize().orElseThrow();
        Assertions.assertTrue(node.is(Node.Group.Variable));
        Assertions.assertEquals("test", node.render());
    }
}