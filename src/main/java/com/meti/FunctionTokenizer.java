package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionTokenizer implements Tokenizer<Node> {
    private final Content content;

    public FunctionTokenizer(Content content) {
        this.content = content;
    }

    @Override
    public Optional<Node> tokenize() {
        OptionalInt startOptional = content.index("(");
        OptionalInt endOptional = content.index(")");
        if (startOptional.isPresent() && endOptional.isPresent()) {
            int start = startOptional.getAsInt();
            int end = endOptional.getAsInt();
            return tokenizeWithParameters(start, end);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Node> tokenizeWithParameters(int start, int end) {
        List<Field> fields = parseFields(start, end);
        Content name = content.slice(4, start);
        OptionalInt returnStartOptional = content.indexFrom(":", end);
        OptionalInt returnEndOptional = content.index("=>");
        if (returnStartOptional.isPresent() && returnEndOptional.isPresent()) {
            int returnStart = returnStartOptional.getAsInt();
            int returnEnd = returnEndOptional.getAsInt();
            return tokenizeValidly(fields, name, returnStart, returnEnd);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Node> tokenizeValidly(List<Field> parameters, Content name, int returnStart, int returnEnd) {
        Type type = new ContentType(content.slice(returnStart + 1, returnEnd));
        Node value = parseContent(returnEnd);
        //TODO: Replace sequence here with monad
        return Optional.of(new FunctionBuilder().withIdentity(name.applyToValue((Function<String, Field>) s -> new InlineField(s, type))).withParameters(parameters).withChild(value).build());
    }

    private Node parseContent(int returnEnd) {
        Content valueContent = content.sliceToEnd(returnEnd + 2);
        return new ContentNode(valueContent);
    }

    private List<Field> parseFields(int start, int end) {
        return content.slice(start + 1, end).split(",")
                .filter(Content::isPresent)
                .map(FieldTokenizer::new)
                .map(FieldTokenizer::tokenize)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}
