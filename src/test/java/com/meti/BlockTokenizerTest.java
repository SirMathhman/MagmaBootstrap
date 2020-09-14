package com.meti;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BlockTokenizerTest {
    @Test
    void validInner(){
        Content content = new RootContent("{{}{}}");
        Tokenizer<Node> tokenizer = new BlockTokenizer(content);
        Optional<Node> optional = tokenizer.tokenize();
        Node result = optional.orElseThrow();
        assertEquals(new BlockNode(new ContentNode(new RootContent("{}")), new ContentNode(new RootContent("{}"))), result);
    }

    @Test
    void validChildren(){
        Content content = new RootContent("{10;20}");
        Tokenizer<Node> tokenizer = new BlockTokenizer(content);
        Optional<Node> optional = tokenizer.tokenize();
        Node result = optional.orElseThrow();
        assertEquals(new BlockNode(new ContentNode(new RootContent("10")), new ContentNode(new RootContent("20"))), result);
    }

    @Test
    void invalid(){
        Content content = new RootContent("test");
        Tokenizer<Node> tokenizer = new BlockTokenizer(content);
        Optional<Node> optional = tokenizer.tokenize();
        assertTrue(optional.isEmpty());
    }

    @Test
    void validChild(){
        Content content = new RootContent("{10}");
        Tokenizer<Node> tokenizer = new RootTokenizer(content);
        Optional<Node> optional = tokenizer.tokenize();
        Node result = optional.orElseThrow();
        assertEquals(new BlockNode(new ContentNode(new RootContent("10"))), result);
    }

    @Test
    void valid(){
        Content content = new RootContent("{}");
        Tokenizer<Node> tokenizer = new BlockTokenizer(content);
        Optional<Node> optional = tokenizer.tokenize();
        Node result = optional.orElseThrow();
        assertEquals(new BlockNode(), result);
    }
}