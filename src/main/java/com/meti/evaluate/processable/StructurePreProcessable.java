package com.meti.evaluate.processable;

import com.meti.process.State;
import com.meti.render.NodeGroup;

import java.util.Optional;

public class StructurePreProcessable extends AbstractProcessable {
    public StructurePreProcessable(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        if (previous.has(NodeGroup.Structure)) {
            return Optional.of(previous.define());
        }
        return Optional.empty();
    }
}
