package com.meti;

import org.junit.jupiter.api.Test;

public class NewTest extends CompileTest {
    @Test
    void test() {
        assertCompile("struct Wrapper{int value;};int main(){struct Wrapper* wrapper=malloc(sizeof(Wrapper));}", "struct Wrapper{value : I16}native def malloc(size : Any) : Void*;def main() : Int => {const wrapper : Wrapper* = new Wrapper;}");
    }
}
