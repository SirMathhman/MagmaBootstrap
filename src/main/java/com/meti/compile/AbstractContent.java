package com.meti.compile;

public abstract class AbstractContent implements Content {
    @Override
    public Content sliceToEnd(int index) {
        var line = 1;
        var indexToUse = 0;
        var value = asString();
        for (int i = 0; i < index; i++) {
            var c = value.charAt(i);
            if (c == '\n') {
                line++;
                indexToUse = 0;
            }
            indexToUse++;
        }
        return new LeafContent(value, value.substring(index), line, indexToUse);
    }
}
