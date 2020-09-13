package com.meti;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;

public class FieldTokenizer implements Tokenizer<Field> {
    private final Content content;

    public FieldTokenizer(Content content) {
        this.content = content;
    }

    @Override
    public Field tokenize() {
        return tokenizeOptionally().orElseThrow();
    }

    @Override
    public Optional<Field> tokenizeOptionally() {
        OptionalInt separatorOptional = content.index(":");
        if (separatorOptional.isPresent()) {
            int separator = separatorOptional.getAsInt();
            Content name = content.slice(0, separator);
            Content typeContent = content.slice(separator + 1);
            Type type = new ContentType(typeContent);
            //TODO: replace with monad
            Field field = name.applyToValue((Function<String, Field>) s -> new InlineField(s, type));
            return Optional.of(field);
        } else {
            return Optional.empty();
        }
    }
}
