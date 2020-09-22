package com.meti.feature.block.function;

import com.meti.feature.block.Block;
import com.meti.feature.render.Node;
import com.meti.feature.type.primitive.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ImplementationTest {
    @Test
    void render(){
        Node node = new Implementation("empty", Collections.emptyList(), PrimitiveType.VOID, new Block());
        assertEquals("void empty(){}", node.render());
    }
}