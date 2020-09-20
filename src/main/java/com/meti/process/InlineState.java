package com.meti.process;

import com.meti.render.Node;
import com.meti.stack.CallStack;
import com.meti.util.Dyad;
import com.meti.util.Monad;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class InlineState implements State {
    private final Node node;
    private final CallStack stack;

    public InlineState(Node node, CallStack stack) {
        this.node = node;
        this.stack = stack;
    }

    @Override
    public Optional<State> foldStackByNode(Predicate<Node> predicate, BiFunction<Node, CallStack, CallStack> mapping) {
        return predicate.test(node) ? Optional.of(new InlineState(node, mapping.apply(node, stack))) : Optional.empty();
    }

    @Override
    public Dyad<Node, CallStack> destroy() {
        return new Dyad<>(node, stack);
    }

    @Override
    public Monad<Node> node() {
        return new Monad<>(node);
    }

    @Override
    public State with(CallStack stack) {
        return new InlineState(node, stack);
    }

    @Override
    public State with(Node node) {
        return new InlineState(node, stack);
    }

    @Override
    public Monad<CallStack> stack() {
        return new Monad<>(stack);
    }
}
