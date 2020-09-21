package com.meti.process;

import com.meti.render.Node;
import com.meti.render.NodeGroup;
import com.meti.stack.CallStack;
import com.meti.util.Dyad;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public interface State {
    default boolean has(NodeGroup group) {
        return node().test(group::matches);
    }

    Optional<State> foldStackByNode(Predicate<Node> predicate, BiFunction<Node, CallStack, CallStack> mapping);

    Dyad<Node, CallStack> destroy();

    Monad<Node> node();

    State with(CallStack stack);

    State with(Node node);

    Monad<CallStack> stack();

    default State define(){
        return destroy().apply((node, stack) -> with(node.define(stack)));
    }
}
