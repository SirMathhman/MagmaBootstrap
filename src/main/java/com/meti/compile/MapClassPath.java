package com.meti.compile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapClassPath implements ClassPath {
    private final Map<List<String>, String> paths;

    public MapClassPath(Map<List<String>, String> paths) {
        this.paths = Collections.unmodifiableMap(paths);
    }

    @Override
    public String read(List<String> list) {
        if (paths.containsKey(list)) {
            return paths.get(list);
        } else {
            String message = String.format("No script with package: %s was found in %s.", String.join(".", list), paths);
            throw new IllegalArgumentException(message);
        }
    }
}
