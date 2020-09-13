package com.meti;

public class Compiler {
    String compile(String content) {
        if(content.startsWith("def")) {
            return "int main(){return 0;}";
        }
        return content;
    }
}