package com.meti.evaluate.processable;

import com.meti.process.State;
import com.meti.render.Field;
import com.meti.render.Node;
import com.meti.render.NodeGroup;
import com.meti.stack.CallStack;

import java.util.List;

public class AbstractionPreProcessable extends FunctionPreProcessable {
    public AbstractionPreProcessable(State previous) {
        super(previous);
    }

    @Override
    protected CallStack after(List<Field> fields, CallStack withIdentity) {
        return withIdentity;
    }

    @Override
    protected boolean test(Node node) {
        return NodeGroup.Abstraction.matches(node);
    }
}
