package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionTokenizer implements Tokenizer<Node> {
    private final Content content;
    private final String string;

    public FunctionTokenizer(Content content, String string) {
        this.content = content;
        this.string = string;
    }

    @Override
    public Node tokenize() {
        return tokenizeOptionally().orElseThrow();
    }

    @Override
    public Optional<Node> tokenizeOptionally() {
        OptionalInt startOptional = content.index("(");
        OptionalInt endOptional = content.index(")");
        if (startOptional.isPresent() && endOptional.isPresent()) {
            int start = startOptional.getAsInt();
            int end = startOptional.getAsInt();
            List<Field> fields = parseFields(start, end);
            Content name = content.slice(4, start);
            OptionalInt returnStartOptional = content.indexFrom(":", end);
            OptionalInt returnEndOptional = content.index("=>");
            if (returnStartOptional.isPresent() && returnEndOptional.isPresent()) {
                int returnStart = returnStartOptional.getAsInt();
                int returnEnd = returnEndOptional.getAsInt();
                Type type = new ContentType(content.slice(returnStart + 1, returnEnd));
                content.sliceToEnd(returnEnd + 2);
                String valueString = string.substring(returnEnd + 2).trim();
                Node value = new ValueNode(valueString);
                return Optional.of(name.applyToValue((Function<String, Node>) s -> new FunctionNode(s, fields, type, value)));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private List<Field> parseFields(int start, int end) {
        return content.slice(start + 1, end).split(",")
                .filter(Content::isPresent)
                .map(FieldTokenizer::new)
                .map(FieldTokenizer::tokenize)
                .collect(Collectors.toList());
    }
}
