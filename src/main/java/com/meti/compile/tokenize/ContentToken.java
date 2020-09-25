package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public class ContentToken implements Token {
    private final Content content;

    public ContentToken(Content content) {
        this.content = content;
    }

    @Override
    public Token form(Function<Content, Token> former) {
        return former.apply(content);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Content;
    }
}
