package com.phiro.betterdeathscreen.util;

import java.util.Locale;

public final class DeathCauseFormatter {
    private DeathCauseFormatter() {
    }

    public static String format(String rawCause) {
        String lower = rawCause.toLowerCase(Locale.ROOT);
        if (lower.contains("fell from a high place")) {
            return "\uD83C\uDF42 Fall Damage";
        }
        if (lower.contains("was slain by") || lower.contains("was killed by")) {
            return "\u2694 " + rawCause;
        }
        if (lower.contains("burned to death") || lower.contains("went up in flames") || lower.contains("was burned")) {
            return "\uD83D\uDD25 Fire";
        }
        if (lower.contains("drowned")) {
            return "\uD83D\uDCA7 Drowned";
        }
        if (lower.contains("fell out of the world") || lower.contains("fell into the void")) {
            return "\uD83C\uDF11 Void";
        }
        if (lower.contains("starved to death") || lower.contains("starved")) {
            return "\uD83C\uDF56 Starvation";
        }
        if (lower.contains("hit the ground too hard")) {
            return "\uD83C\uDF42 Fall Damage";
        }
        if (lower.contains("was shot by")) {
            return "\uD83C\uDFF9 " + rawCause;
        }
        if (lower.contains("blew up") || lower.contains("was blown up")) {
            return "\uD83D\uDCA5 Explosion";
        }
        return rawCause;
    }
}
