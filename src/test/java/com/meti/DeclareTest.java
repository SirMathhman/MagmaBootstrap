package com.meti;

import org.junit.jupiter.api.Test;

public class DeclareTest extends CompileTest{
    @Test
    void define(){
        assertCompile("int main(){int x=5;return 0;}", "def main() : Int => {const x : Int = 5;return 0;}");
    }
}
