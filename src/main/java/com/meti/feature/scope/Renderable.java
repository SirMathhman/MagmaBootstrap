package com.meti.feature.scope;

import java.util.Optional;

public interface Renderable {
    @Deprecated
    Optional<String> renderOptionally();

    String render();
}
