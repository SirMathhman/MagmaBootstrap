package com.meti.evaluate.processable.finalize;

import com.meti.evaluate.processable.AbstractProcessable;
import com.meti.process.State;
import com.meti.feature.Field;
import com.meti.feature.Node;
import com.meti.stack.CallStack;

import java.util.Optional;

public class DeclareDefiner extends AbstractProcessable {
    public DeclareDefiner(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        if (previous.node().test(this::isDeclaration)) {
            CallStack newStack = previous.destroy().apply(this::define);
            return Optional.ofNullable(previous.with(newStack));
        }
        return Optional.empty();
    }

    private CallStack define(Node declaration, CallStack callStack) {
        Field identity = findIdentity(declaration);
        return callStack.define(identity);
    }

    private Field findIdentity(Node node) {
        return node.streamFields()
                .findFirst()
                .orElseThrow();
    }

    private boolean isDeclaration(Node node) {
        return node.group().test(Node.Group.Declare.matches());
    }
}
