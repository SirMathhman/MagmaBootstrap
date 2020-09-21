package com.meti.evaluate.processable.fix;

import com.meti.evaluate.processable.AbstractProcessable;
import com.meti.process.State;
import com.meti.render.Field;
import com.meti.render.Node;
import com.meti.render.NodeGroup;
import com.meti.type.Type;

import java.util.Optional;

public class DeclareFixer extends AbstractProcessable {
    public DeclareFixer(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        if (previous.has(NodeGroup.Declare)) {
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
