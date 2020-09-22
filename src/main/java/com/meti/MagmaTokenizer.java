package com.meti;

import com.meti.content.Content;
import com.meti.feature.CompileException;
import com.meti.feature.evaluate.tokenize.MagmaNodeTokenizer;
import com.meti.feature.render.Node;
import com.meti.feature.render.Type;
import com.meti.resolve.MagmaTypeTokenizer;
import com.meti.util.load.ClassPath;

import java.util.Optional;
import java.util.function.Supplier;

public class MagmaTokenizer implements Tokenizer {
    private final ClassPath classPath;

    public MagmaTokenizer(ClassPath classPath) {
        this.classPath = classPath;
    }

    @Override
    public Node tokenize(Node previous) {
        Optional<Node> optional = previous.applyToContent((Content content) -> parseContent(content, classPath));
        if (optional.isPresent()) {
            Node node = optional.orElseThrow();
            Node.Prototype prototype = node.createPrototype();
            Node.Prototype withFields = node.streamFields()
                    .map(field -> field.transformByType(this::resolve))
                    .reduce(prototype, Node.Prototype::withField, (previous1, next) -> next);
            Node.Prototype withChildren = node.streamChildren()
                    .map(this::tokenize)
                    .reduce(withFields, Node.Prototype::withChild, (previous1, next) -> next);
            return withChildren.build();
        } else {
            return previous;
        }
    }

    private CompileException invalidateType(Type previous) {
        return previous.applyToContent(this::invalidateType).orElseThrow();
    }

    private CompileException invalidateType(Content content) {
        return content.value().apply(this::invalidateType);
    }

    private CompileException invalidateType(String s) {
        String message = String.format("Unable to resolve type: %s", s);
        return new CompileException(message);
    }

    Type resolve(Type previous) {
        Type parent = tokenizeType(previous);
        Type.Prototype prototype = parent.createPrototype();
        Type.Prototype withFields = parent.streamFields()
                .map(field -> field.transformByType(this::resolve))
                .reduce(prototype, Type.Prototype::withField, (oldPrototype, newPrototype) -> newPrototype);
        Type.Prototype withChildren = parent.streamChildren()
                .map(this::resolve)
                .reduce(withFields, Type.Prototype::withChild, (oldPrototype, newPrototype) -> newPrototype);
        return withChildren.build();
    }

    private Type tokenizeType(Type previous) {
        return previous.is(Type.Group.Content) ?
                new MagmaTypeTokenizer(previous).tokenize().orElseThrow(() -> this.invalidateType(previous)) : previous;
    }

    private Node parseContent(Content content, ClassPath classPath) {
        return new MagmaNodeTokenizer(content, classPath)
                .evaluate()
                .orElseThrow(supplyInvalidParse(content));
    }

    private Supplier<IllegalArgumentException> supplyInvalidParse(Content content) {
        return () -> content.value().append("Cannot parse: %s")
                .swap().apply(String::format)
                .transform(IllegalArgumentException::new);
    }
}