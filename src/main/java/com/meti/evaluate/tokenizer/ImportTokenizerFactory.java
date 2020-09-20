package com.meti.evaluate.tokenizer;

import com.meti.content.Content;

public class ImportTokenizerFactory {
    public ImportTokenizerFactory() {
    }

    public ImportTokenizer create(Content content1) {
        return new ImportTokenizer(content1);
    }
}