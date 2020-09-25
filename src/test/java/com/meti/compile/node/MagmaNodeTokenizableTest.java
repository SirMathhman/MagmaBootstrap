package com.meti.compile.node;

import com.meti.compile.RootContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MagmaNodeTokenizableTest {
    @Test
    void testVariable() {
        var content = new RootContent("test");
        var tokenizable = new MagmaNodeTokenizable(content);
        var optional = tokenizable.tokenize();
        var token = optional.orElseThrow();
        assertTrue(token.is(Node.Group.Variable));
        assertEquals("test", token.render());
    }
}