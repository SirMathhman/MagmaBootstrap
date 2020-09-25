package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeafContentTest {

    @Test
    void asString() {
        Content content = new LeafContent("something", "thing", 0, 0);
        assertEquals("thing", content.asString());
    }

    @Test
    void format() {
        Content content = new LeafContent("\nsomething", "thing", 1, 4);
        assertEquals("1 4 some>>thing<<", content.format("%d %d %s"));
    }

    @Test
    void startsWith() {
        Content content = new LeafContent("something", "something", 0, 0);
        assertTrue(content.startsWith("some"));
    }
}