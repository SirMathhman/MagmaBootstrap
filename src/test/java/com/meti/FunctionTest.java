package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    @Test
    void testMain() {
        Compiler compiler = new Compiler();
        String target = compiler.compile("def main() : Int => {return 0;}");
        assertEquals("int main(){return 0;}", target);
    }

    @Test
    void value(){
        Compiler compiler = new Compiler();
        String target = compiler.compile("def main() : Void => {return 0;}");
        assertEquals("void main(){return 0;}", target);
    }

    @Test
    void name(){
        Compiler compiler = new Compiler();
        String target = compiler.compile("def test : Void => {return 0;}");
        assertEquals("void test(){return 0;}", target);
    }
}