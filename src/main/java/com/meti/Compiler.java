package com.meti;

public class Compiler {
    String compile(String content) {
        if (content.startsWith("def")) {
            int paramStart = content.indexOf('(');
            String name = content.substring(4, paramStart).trim();
            int returnStart = content.indexOf(':');
            int returnEnd = content.indexOf("=>");
            String returnString = content.substring(returnStart + 1, returnEnd).trim();
            String renderHeader = resolveString(returnString, name);
            String value = content.substring(returnEnd + 2).trim();
            return renderHeader + "()" + value;
        }
        return content;
    }

    private String resolveString(String value, String name) {
        String renderHeader;
        if (value.equals("Int")) {
            renderHeader = "int " + name;
        } else {
            renderHeader = "void " + name;
        }
        return renderHeader;
    }
}