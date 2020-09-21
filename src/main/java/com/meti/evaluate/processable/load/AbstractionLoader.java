package com.meti.evaluate.processable.load;

import com.meti.process.State;
import com.meti.feature.Field;
import com.meti.feature.Node;
import com.meti.stack.CallStack;

import java.util.List;

public class AbstractionLoader extends FunctionLoader {
    public AbstractionLoader(State previous) {
        super(previous);
    }

    @Override
    protected CallStack after(List<Field> fields, CallStack withIdentity) {
        return withIdentity;
    }

    @Override
    protected boolean test(Node node) {
        return Node.Group.Abstraction.matches(node);
    }
}
