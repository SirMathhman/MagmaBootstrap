package com.meti.compile.tokenize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void form() {
        Token token = new Variable("test");
        assertThrows(UnformableException.class, () -> token.form(null));
    }

    @Test
    void is() {
        Token token = new Variable("test");
        assertTrue(token.is(Token.Group.Variable));
    }
}