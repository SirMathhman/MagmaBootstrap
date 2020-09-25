package com.meti.compile;

public class RootContent implements Content {
    private final String value;

    public RootContent(String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value.trim();
    }
}
