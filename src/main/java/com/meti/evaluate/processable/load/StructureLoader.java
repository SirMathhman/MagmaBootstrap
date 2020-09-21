package com.meti.evaluate.processable.load;

import com.meti.evaluate.processable.AbstractProcessable;
import com.meti.process.State;
import com.meti.render.NodeGroup;

import java.util.Optional;

public class StructureLoader extends AbstractProcessable {
    public StructureLoader(State previous) {
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
