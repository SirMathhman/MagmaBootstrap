package com.meti.compile.tokenize;

import com.meti.compile.RootContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTokenTest {

    @Test
    void form() {
        var content = new RootContent("test");
        var token = new ContentToken(content);
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
        var token = new ContentToken(content);
        assertTrue(token.is(Node.Group.Content));
    }

    @Test
    void render() {
        var content = new RootContent("test");
        var token = new ContentToken(content);
        assertThrows(UnrenderableException.class, token::render);
    }

    @Test
    void transformChildren() {
        var content = new RootContent("test");
        var token = new ContentToken(content);
        assertThrows(UntransformableException.class, () -> token.transformChildren(null));
    }
}