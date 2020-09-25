package com.meti.compile;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapClassPathTest {

    @Test
    void read() {
        ClassPath path = new MapClassPath(Map.of(List.of("test"), "test"));
        assertEquals("test", path.read(List.of("test")));
    }

    @Test
    void readNotPresent() {
        ClassPath path = new MapClassPath(Collections.emptyMap());
        assertThrows(IllegalArgumentException.class, () -> path.read(Collections.emptyList()));
    }
}