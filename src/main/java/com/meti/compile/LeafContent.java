package com.meti.compile;

public class LeafContent extends AbstractContent {
    private final String context;
    private final int line;
    private final int index;
    private final String value;

    public LeafContent(String context, String value, int line, int index) {
        this.context = context;
        this.line = line;
        this.index = index;
        this.value = value;
    }

    @Override
    public String asString() {
        return value.trim();
    }

    @Override
    public String format(String message) {
        var replacement = ">>" + value + "<<";
        var replace = context.replace(value, replacement);
        var format = replace.trim();
        return String.format(message, line, index, format);
    }

    @Override
    public boolean startsWith(String prefix) {
        return asString().startsWith(prefix);
    }
}
