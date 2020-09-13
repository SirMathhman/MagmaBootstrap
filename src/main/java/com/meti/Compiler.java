package com.meti;

public class Compiler {
    String compile(String content) {
        if(content.startsWith("def")) {
            int paramStart = content.indexOf('(');
            String name = content.substring(4, paramStart).trim();
            return String.format("int %s(){return 0;}", name);
        }
        return content;
    }
}