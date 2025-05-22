package com.green.energy.tracker.perf.test.util;

import java.util.Objects;

public final class Properties {
    public static int envOrElse(String name, int value) {
        String envValue = System.getenv(name);
        return Objects.nonNull(envValue) ? Integer.parseInt(envValue) : value;
    }
}
