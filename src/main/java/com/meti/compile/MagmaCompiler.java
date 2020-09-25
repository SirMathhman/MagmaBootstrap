package com.meti.compile;

import com.meti.compile.tokenize.MagmaTokenTokenizer;

import java.util.List;

public class MagmaCompiler implements Compiler {
    public static final MagmaTokenTokenizer TOKENIZER = new MagmaTokenTokenizer();

    @Override
    public String compile(ClassPath path, List<String> mainPackage) {
        var main = path.read(mainPackage);
        var content = new RootContent(main);
        var token = TOKENIZER.tokenize(content);
        return token.render();
    }
}
