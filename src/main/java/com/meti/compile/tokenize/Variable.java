package com.meti.compile.tokenize;

import com.meti.compile.Content;

import java.util.function.Function;

public class Variable implements Token{
    private final String value;

    Variable(String value) {
        this.value = value;
    }

    @Override
    public Token form(Function<Content, Token> former) {
        var message = String.format("Cannot form node of type %s", getClass());
        throw new UnformableException(message);
    }

    @Override
    public boolean is(Group group) {
        return group == Group.Variable;
    }
}
