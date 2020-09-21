package com.meti.evaluate.processable;

import com.meti.evaluate.processable.finalize.DeclareDefiner;
import com.meti.evaluate.processable.fix.ProcedureFixer;
import com.meti.process.State;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaPostProcessable extends CollectiveProcessable{
    public MagmaPostProcessable(State previous) {
        super(previous);
    }

    @Override
    protected Stream<Function<State, Processable>> streamPreprocessors() {
        return Stream.of(
                DeclareDefiner::new,
                ProcedureFixer::new
        );
    }
}
