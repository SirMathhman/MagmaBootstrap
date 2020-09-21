package com.meti.feature.render;

import com.meti.feature.evaluate.resolve.Resolver;
import com.meti.feature.block.invoke.MappingResolver;
import com.meti.feature.evaluate.resolve.CollectiveResolver;
import com.meti.feature.scope.variable.VariableResolver;
import com.meti.process.State;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaResolver extends CollectiveResolver {
    public MagmaResolver(State value) {
        super(value);
    }

    @Override
    protected Stream<Function<State, Resolver>> streamResolvers() {
        return Stream.of(MappingResolver::new, VariableResolver::new);
    }
}
