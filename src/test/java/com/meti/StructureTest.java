package com.meti;

import org.junit.jupiter.api.Test;

public class StructureTest extends CompileTest {
    @Test
    void emptyDefine() {
        assertCompile("struct Empty{};int main(){return 0;}", "struct Empty{}def main() : I16 => {return 0;}");
    }

    @Test
    void singleDefine() {
        assertCompile("struct Wrapper{int value;};int main(){return 0;}", "struct Wrapper{value : I16}def main() : I16 => {return 0;}");
    }

    @Test
    void multipleDefine() {
        assertCompile("struct Pair{unsigned char value0;unsigned int value1;};int main(){return 0;}", "struct Pair{value0 : U8;value1 : U16}def main() : I16 => {return 0;}");
    }

    @Test
    void emptyConstruct() {
        assertCompile("", "struct Empty{}def main() : I16 => {const value : Empty = <Empty>{}}; return 0;}");
    }

    @Test
    void singleConstruct() {
        assertCompile("", "struct Wrapper{value : I16}def main() : I16 => {const value : Wrapper = <Wrapper>{16}; return 0;}");
    }

    @Test
    void multipleConstruct() {
        assertCompile("", "struct Pair{value0 : U8;value1 : U16}def main() : I16 => {const value : Pair = <Pair>{2, 2}; return 0;}");
    }
}
