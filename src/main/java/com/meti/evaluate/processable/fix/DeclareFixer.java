package com.meti.evaluate.processable.fix;

import com.meti.evaluate.processable.AbstractProcessable;
import com.meti.process.State;
import com.meti.feature.Field;
import com.meti.feature.Node;
import com.meti.type.Type;

import java.util.Optional;

public class DeclareFixer extends AbstractProcessable {
    public DeclareFixer(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        if (previous.has(Node.Group.Declare)) {
            return Optional.of(previous.transformByNode(this::transformImpl));
        }
        return Optional.empty();
    }

    private Node transformImpl(Node node) {
        Node.Prototype prototype = node.transformByIdentity(this::transformIdentity);
        return node.createWithChildren()
                .merge(prototype)
                .build();
    }

    private Field transformIdentity(Field oldIdentity) {
        return oldIdentity.transformByType(this::fixDeclareType);
    }

    private Type fixDeclareType(Type type) {
        return type;
    }
}
