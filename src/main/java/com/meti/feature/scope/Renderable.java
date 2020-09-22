package com.meti.feature.scope;

import com.meti.feature.render.UnrenderableException;

import java.util.Optional;

public interface Renderable {
    Optional<String> renderOptionally();

    default String render() {
        return renderOptionally().orElseThrow(() -> new UnrenderableException("Not renderable."));
    }
}
