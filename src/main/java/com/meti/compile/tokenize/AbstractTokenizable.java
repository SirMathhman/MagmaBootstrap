package com.meti.compile.tokenize;

import com.meti.compile.Content;

public abstract class AbstractTokenizable<T extends Token> implements Tokenizable<T> {
    protected final Content content;

    protected AbstractTokenizable(Content content) {
        this.content = content;
    }
}
