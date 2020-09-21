package com.meti.feature;

import com.meti.content.Content;
import com.meti.evaluate.Evaluator;
import com.meti.feature.scope.NewTokenizer;
import com.meti.feature.block.function.ReturnTokenizer;
import com.meti.feature.type.point.DereferenceTokenizer;
import com.meti.feature.type.point.ReferenceTokenizer;
import com.meti.feature.type.primitive.IntTokenizer;
import com.meti.feature.type.primitive.StringTokenizer;
import com.meti.feature.type.structure.ConstructionTokenizer;
import com.meti.feature.type.structure.FieldTokenizer;
import com.meti.feature.type.structure.StructureTokenizer;
import com.meti.feature.block.BlockTokenizer;
import com.meti.feature.block.function.FunctionTokenizer;
import com.meti.feature.block.invoke.InvocationTokenizer;
import com.meti.feature.extern.ImportTokenizerFactory;
import com.meti.feature.scope.DeclarationTokenizer;
import com.meti.feature.scope.VariableTokenizer;
import com.meti.feature.type.*;
import com.meti.util.load.ClassPath;

import java.util.function.Function;
import java.util.stream.Stream;

public class MagmaNodeTokenizer extends CollectiveNodeTokenizer {
    private final ImportTokenizerFactory factory;

    public MagmaNodeTokenizer(Content content, ClassPath classPath) {
        super(content);
        this.factory = new ImportTokenizerFactory(classPath);
    }

    @Override
    protected Stream<Function<Content, Evaluator<Node>>> streamFactories() {
        return Stream.of(
                factory::create,
                StringTokenizer::new,
                NewTokenizer::new,
                CastTokenizer::new,
                ConstructionTokenizer::new,
                StructureTokenizer::new,
                DereferenceTokenizer::new,
                ReferenceTokenizer::new,
                ReturnTokenizer::new,
                InvocationTokenizer::new,
                BlockTokenizer::new,
                FunctionTokenizer::new,
                IntTokenizer::new,
                DeclarationTokenizer::new,
                FieldTokenizer::new,
                VariableTokenizer::new
        );
    }

}
