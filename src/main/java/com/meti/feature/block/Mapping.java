package com.meti.feature.block;

import com.meti.feature.Node;
import com.meti.util.Monad;

import java.util.ArrayList;
import java.util.List;

public class Mapping extends Invocation {
    public Mapping(Node caller, Node... arguments) {
        this(caller, List.of(arguments));
    }

    public Mapping(Node caller, List<Node> arguments) {
        super(caller, arguments);
    }

    @Override
    protected String createExtension() {
        return "";
    }

    @Override
    public Monad<Group> group() {
        return new Monad<>(Group.Mapping);
    }

    @Override
    public Prototype createPrototype() {
        return new MappingPrototype();
    }

    private static class MappingPrototype extends InvocationPrototype {
        private MappingPrototype() {
            this(new ArrayList<>());
        }

        private MappingPrototype(List<Node> children) {
            super(children);
        }

        @Override
        protected Node build(Node caller, List<Node> arguments) {
            return new Mapping(caller, arguments);
        }

        @Override
        protected Prototype copy(List<Node> newChildren) {
            return new MappingPrototype(newChildren);
        }
    }
}
