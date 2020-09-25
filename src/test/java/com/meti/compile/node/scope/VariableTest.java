package com.meti.compile.node.scope;

import com.meti.compile.node.Node;
import com.meti.compile.tokenize.UnformableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void form() {
        var token = new Variable("test");
        assertThrows(UnformableException.class, () -> token.form(null));
    }

    @Test
    void is() {
        var token = new Variable("test");
        assertTrue(token.is(Node.Group.Variable));
    }

    @Test
    void transformChildren() {
        var expected = new Variable("test");
        var actual = expected.transformChildren(null);
        assertSame(expected, actual);
    }

    @Test
    void render() {
        var node = new Variable("test");
        assertEquals("test", node.render());
    }
}