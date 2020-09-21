package com.meti.feature.block.invoke;

import com.meti.feature.evaluate.process.AbstractProcessable;
import com.meti.feature.render.MagmaResolver;
import com.meti.feature.evaluate.resolve.Resolver;
import com.meti.process.State;
import com.meti.feature.render.Node;
import com.meti.feature.type.primitive.PrimitiveType;
import com.meti.feature.scope.Type;
import com.meti.util.Monad;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcedureFixer extends AbstractProcessable {
    public ProcedureFixer(State previous) {
        super(previous);
    }

    @Override
    public Optional<State> evaluate() {
        if (previous.node().test(this::isMapping)) {
            Monad<Type> type = findType();
            if (type.test(value -> value == PrimitiveType.VOID)) {
                Node toReturn = previous.node().apply(this::mapToProcedure);
                return Optional.ofNullable(previous.with(toReturn));
            } else {
                return Optional.of(previous);
            }
        }
        return Optional.empty();
    }

    private Procedure mapToProcedure(Node node) {
        List<Node> children = node.streamChildren().collect(Collectors.toList());
        Node caller = children.get(0);
        List<Node> arguments = children.subList(1, children.size());
        return new Procedure(caller, arguments);
    }

    private Monad<Type> findType() {
        Resolver resolver = new MagmaResolver(previous);
        return resolver.resolve().orElseThrow(this::createResolutionException);
    }

    private IllegalStateException createResolutionException() {
        String message = previous.node().apply(this::createResolutionMessage);
        return new IllegalStateException(message);
    }

    private String createResolutionMessage(Node node) {
        return String.format("Unable to resolve mapping: '%s'", node);
    }

    private boolean isMapping(Node value) {
        return value.group().test(Node.Group.Mapping.matches());
    }
}
