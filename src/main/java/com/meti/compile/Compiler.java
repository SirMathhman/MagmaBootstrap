package com.meti.compile;

import java.util.List;

public interface Compiler {
    String compile(ClassPath path, List<String> mainPackage);
}
