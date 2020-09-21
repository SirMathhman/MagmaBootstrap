package com.meti.evaluate.processable.fix;

import com.meti.evaluate.processable.AbstractProcessable;
import com.meti.process.State;

import java.util.Optional;

public class DeclareFixer extends AbstractProcessable {
    public DeclareFixer(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        return Optional.empty();
    }
}
