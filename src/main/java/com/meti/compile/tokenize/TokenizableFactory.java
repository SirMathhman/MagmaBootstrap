package com.meti.compile.tokenize;

import com.meti.compile.Content;

public interface TokenizableFactory<T extends Token> {
    Tokenizable<T> create(Content content);
}
