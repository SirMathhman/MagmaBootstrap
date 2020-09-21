package com.meti.evaluate.processable;

import com.meti.evaluate.processable.load.AbstractionLoader;
import com.meti.evaluate.processable.load.ImplementationLoader;
import com.meti.evaluate.processable.load.StructureLoader;
import com.meti.process.State;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaPreProcessable extends CollectiveProcessable {
    public MagmaPreProcessable(State previous) {
        super(previous);
    }

    @Override
    protected Stream<Function<State, Processable>> streamPreprocessors() {
        return Stream.of(
                StructureLoader::new,
                ImplementationLoader::new,
                AbstractionLoader::new
        );
    }
}
