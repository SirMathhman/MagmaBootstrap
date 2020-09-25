package com.meti.compile;

import java.util.Collections;

public class Compiler {
    public String compile(ClassPath path) {
        String main = path.read(Collections.emptyList());
        Content content = new RootContent(main);
        return null;
    }
}
