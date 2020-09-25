package com.meti.compile;

import com.meti.compile.tokenize.ContentToken;
import com.meti.compile.tokenize.Token;

import java.util.Collections;
import java.util.function.Function;

public class Compiler {
    public String compile(ClassPath path) {
        var main = path.read(Collections.emptyList());
        var content = new RootContent(main);
        var token = new ContentToken(content);
        token.form(new Function<Content, Token>() {
            @Override
            public Token apply(Content content) {
                return null;
            }
        });
        return null;
    }
}
