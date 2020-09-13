package com.meti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    @Test
    void singleParameter(){
        compile("int pass(int value){return value;}", "def pass(value : Int) : Int => {return value;}");
    }

    @Test
    void multipleParameters(){
        compile("int apply(int start,int end){}", "def apply(start : Int, end : Int) : Int => {}");
    }

    @Test
    void empty() {
        compile("void test(){}", "def test() : Void => {}");
    }

    @Test
    void testMain() {
        compile("int main(){return 0;}", "def main() : Int => {return 0;}");
    }

    private void compile(String expectedTarget, String source) {
        Compiler compiler = new Compiler();
        String actualTarget = compiler.compile(source);
        assertEquals(expectedTarget, actualTarget);
    }

    @Test
    void value() {
        compile("void main(){return 0;}", "def main() : Void => {return 0;}");
    }

    @Test
    void name() {
        compile("int test(){return 0;}", "def test() : Int => {return 0;}");
    }
}