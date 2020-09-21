package com.meti.evaluate.processable.load;

import com.meti.stack.CallStack;
import com.meti.process.State;
import com.meti.feature.Field;
import com.meti.feature.Node;

import java.util.List;

public class ImplementationLoader extends FunctionLoader {
    public ImplementationLoader(State previous) {
        super(previous);
    }

    @Override
    protected CallStack after(List<Field> fields, CallStack withIdentity) {
        List<Field> parameters = fields.subList(1, fields.size());
        return withIdentity.enter().defineAll(parameters);
    }

    @Override
    protected boolean test(Node node) {
        return Node.Group.Implementation.matches(node);
    }
}
