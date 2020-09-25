package com.meti.compile.node;

import com.meti.compile.RootContent;
import com.meti.compile.node.scope.Variable;
import com.meti.compile.tokenize.UnrenderableException;
import com.meti.compile.tokenize.UntransformableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentNodeTest {

    @Test
    void form() {
        var content = new RootContent("test");
        var token = new ContentNode(content);
        var expected = new Variable(null);
        var actual = token.form(given -> {
            assertSame(content, given);
            return expected;
        });
        assertSame(actual, expected);
    }

    @Test
    void is() {
        var content = new RootContent("test");
        var token = new ContentNode(content);
        assertTrue(token.is(Node.Group.Content));
    }

    @Test
    void render() {
        var content = new RootContent("test");
        var token = new ContentNode(content);
        assertThrows(UnrenderableException.class, token::render);
    }

    @Test
    void transformChildren() {
        var content = new RootContent("test");
        var token = new ContentNode(content);
        assertThrows(UntransformableException.class, () -> token.transformChildren(null));
    }
}