package com.meti.compile;

import java.util.Collections;

public class Compiler {
    public String compile(ClassPath path) {
        var main = path.read(Collections.emptyList());
        var content = new RootContent(main);
        return null;
    }
}
