package com.meti;

public abstract class AbstractTokenizer implements Tokenizer<Node> {
    protected final Content content;

    public AbstractTokenizer(Content content) {
        this.content = content;
    }
}
