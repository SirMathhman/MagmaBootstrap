package com.meti.evaluate.processable;

import com.meti.stack.CallStack;
import com.meti.render.NodeGroup;
import com.meti.process.State;
import com.meti.render.Field;
import com.meti.render.Node;

import java.util.List;

public class ImplementationPreProcessable extends FunctionPreProcessable {
    public ImplementationPreProcessable(State previous) {
        super(previous);
    }

    @Override
    protected CallStack after(List<Field> fields, CallStack withIdentity) {
        List<Field> parameters = fields.subList(1, fields.size());
        return withIdentity.enter().defineAll(parameters);
    }

    @Override
    protected boolean test(Node node) {
        return NodeGroup.Implementation.matches(node);
    }
}
