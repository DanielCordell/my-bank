package com.abc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantProvider {
    private static InstantProvider instance = null;

    public static InstantProvider getInstance() {
        if (instance == null)
            instance = new InstantProvider();
        return instance;
    }

    public Instant now() { return Instant.now(); }
    public Instant daysAgo(int days) { return now().minus(days, ChronoUnit.DAYS); }
}
