package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RootContentTest {
    @Test
    void asString() {
        var content = new RootContent(" something\t\n");
        assertEquals("something", content.asString());
    }

    @Test
    void format() {
        var content = new RootContent("value");
        assertEquals("0 0 >>value<<", content.format("%d %d %s"));
    }

    @Test
    void startsWith() {
        var content = new RootContent("something");
        assertTrue(content.startsWith("some"));
    }

    @Test
    void sliceToEnd() {
        var content = new RootContent("\nsomething");
        var child = content.sliceToEnd(4);
        var message = child.format("%d %d %s");
        assertEquals("1 4 some>>thing<<", message);
    }
}