package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public interface Token {
    Token form(Function<Content, Token> former);

    boolean is(Group group);

    enum Group {
        Variable, Content
    }
}
