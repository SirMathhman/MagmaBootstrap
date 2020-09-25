package com.meti.compile.tokenize;

import com.meti.compile.Content;

public interface Tokenizer<T extends Token> {
    T tokenize(Content content);
}
