package com.meti.compile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootContentTest {
    @Test
    void asString(){
        Content content = new RootContent(" something\t\n");
        assertEquals("something", content.asString());
    }

    @Test
    void format() {
        Content content = new RootContent("value");
        assertEquals("0 0 >>value<<", content.format("%d %d %s"));
    }
}