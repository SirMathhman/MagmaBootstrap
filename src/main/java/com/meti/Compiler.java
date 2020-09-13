package com.meti;

public class Compiler {

    String compile(String content) {
        Node node = parseChild(new ContentNode(new RootContent(content)));
        Node result = parse(node);
        return result.render().orElseThrow();
    }

    private Node parse(Node node) {
        Node.Prototype prototype = node.createPrototype();
        Node.Prototype withFields = node.streamFields()
                .map(this::resolveField)
                .reduce(prototype, Node.Prototype::withField, (previous, next) -> next);
        Node.Prototype withChildren = node.streamChildren()
                .map(this::parseChild)
                .reduce(withFields, Node.Prototype::withChild, (previous, next) -> next);
        return withChildren.build();
    }

    private Field resolveField(Field field) {
        Type newType = field.applyToType(this::resolve);
        return field.copy(newType);
    }

    private Node parseChild(Node previous) {
        return previous.applyToContent(this::parseContent).orElseThrow();
    }

    private Node parseContent(Content content) {
        if (content.applyToValue(value -> value.startsWith("def"))) {
            Tokenizer<Node> tokenizer = new FunctionTokenizer(content, null);
            return tokenizer.tokenize();
        } else {
            throw new IllegalArgumentException("Cannot parse: " + content);
        }
    }


    @Deprecated
    private Node parseString(String content) {
        if (content.startsWith("def")) {
            Tokenizer<Node> tokenizer = new FunctionTokenizer(null, content);
            return tokenizer.tokenize();
        } else {
            throw new IllegalArgumentException("Cannot parse: " + content);
        }
    }

    private Type resolve(Type previous) {
        Type type;
        if(previous.applyToContent(content -> content.applyToValue("Int"::equals)).orElseThrow()){
            type = IntType.IntType;
        } else {
            type = VoidType.VoidType;
        }
        return type;
    }
}