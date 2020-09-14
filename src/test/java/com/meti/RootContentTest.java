package com.meti;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class RootContentTest {

    @Test
    void applyToValue() {
        String expected = "test";
        Content content = new RootContent(expected);
        String actual = content.value().apply(Function.identity());
        assertSame(expected, actual);
    }

    @Test
    void slice() {
        Content content = new RootContent("test");
        Content child = content.slice(1, 2);
        String actual = child.value().apply(Function.identity());
        assertEquals("e", actual);
    }

    @Test
    void validateIndex() {
        Content content = new RootContent("something");
        OptionalInt optional = content.index("t");
        assertTrue(optional.isPresent());
    }

    @Test
    void index() {
        Content content = new RootContent("something");
        OptionalInt optional = content.index("t");
        assertEquals(4, optional.orElseThrow());
    }

    @Test
    void sliceToEnd() {
        Content content = new RootContent("test");
        Content child = content.sliceToEnd(1);
        String value = child.value().apply(Function.identity());
        assertEquals("est", value);
    }

    @Test
    void validateIndexFromEnd() {
        Content content = new RootContent("test");
        OptionalInt optional = content.indexFrom("t", 1);
        assertTrue(optional.isPresent());
    }

    @Test
    void indexFrom(){
        Content content = new RootContent("test");
        OptionalInt optionalInt = content.indexFrom("t", 1);
        assertEquals(3, optionalInt.orElseThrow());
    }

    @Test
    void isPresent() {
        Content content = new RootContent("test");
        assertTrue(content.isPresent());
    }
}