package com.meti.compile;

public interface Content {
    String asString();

    String format(String message);

    boolean startsWith(String prefix);

    Content sliceToEnd(int index);
}
