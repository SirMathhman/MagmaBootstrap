package com.meti.resolve;

import com.meti.feature.type.StructureType;
import com.meti.feature.Type;

import java.util.Optional;

public class StructTypeTokenizer extends AbstractTypeTokenizer {
    public StructTypeTokenizer(Type previous) {
        super(previous);
    }

    @Override
    public Optional<Type> tokenize() {
        return previous.applyToContent(StructureType::new);
    }
}
