package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    @Test
    void compile() {
        Compiler compiler = new Compiler();
        String target = compiler.compile("def main() : Int => {return 0;}");
        assertEquals("int main(){return 0;}", target);
    }
}