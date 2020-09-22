package com.meti;

import com.meti.content.Content;
import com.meti.content.StringContent;
import com.meti.feature.*;
import com.meti.feature.evaluate.tokenize.MagmaNodeTokenizer;
import com.meti.feature.render.ContentNode;
import com.meti.feature.render.Node;
import com.meti.feature.render.Type;
import com.meti.util.load.ClassPath;
import com.meti.process.MagmaProcessor;
import com.meti.process.Processor;
import com.meti.resolve.MagmaTypeTokenizer;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Compiler {
    public final ClassPath classPath;

    public Compiler(ClassPath classPath) {
        this.classPath = classPath;
    }

    String compile(String content) {
        Node root = new ContentNode(new StringContent("{" + content + "}"));
        Node tree = tokenize(root);
        Processor processor = new MagmaProcessor();
        String output = processor.process(tree)
                .apply(Node::renderOptionally)
                .orElseThrow();
        return output.substring(1, output.length() - 1);
    }

    private Node tokenize(Node previous) {
        Optional<Node> optional = previous.applyToContent(this::parseContent);
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

    private Node parseContent(Content content) {
        return new MagmaNodeTokenizer(content, classPath)
                .evaluate()
                .orElseThrow(supplyInvalidParse(content));
    }

    private Supplier<IllegalArgumentException> supplyInvalidParse(Content content) {
        return () -> content.value().append("Cannot parse: %s")
                .swap().apply(String::format)
                .transform(IllegalArgumentException::new);
    }

    private Type resolve(Type previous) {
        Type parent;
        //TODO: simplify condition
        if (previous.applyToContent(Function.identity()).isPresent() && previous.group().test(Type.Group.Content.matches())) {
            parent = new MagmaTypeTokenizer(previous).tokenize().orElseThrow(() -> invalidateType(previous));
        } else {
            parent = previous;
        }
        Type.Prototype prototype = parent.createPrototype();
        Type.Prototype withFields = parent.streamFields()
                .map(field -> field.transformByType(this::resolve))
                .reduce(prototype, Type.Prototype::withField, (oldPrototype, newPrototype) -> newPrototype);
        Type.Prototype withChildren = parent.streamChildren()
                .map(this::resolve)
                .reduce(withFields, Type.Prototype::withChild, (oldPrototype, newPrototype) -> newPrototype);
        return withChildren.build();
    }

    private CompileException invalidateType(Type previous) {
        return previous.applyToContent(this::invalidateType).orElseThrow();
    }

    private CompileException invalidateType(Content content) {
        return content.value().apply(Compiler.this::invalidateType);
    }

    private CompileException invalidateType(String s) {
        String message = String.format("Unable to resolve type: %s", s);
        return new CompileException(message);
    }
}