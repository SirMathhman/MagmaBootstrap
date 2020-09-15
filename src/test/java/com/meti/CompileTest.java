package com.meti;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompileTest {
    protected void assertCompile(String expectedTarget, String source) {
        Compiler compiler = new Compiler();
        String actualTarget = compiler.compile(source);
        assertEquals(expectedTarget, actualTarget);
    }
}
