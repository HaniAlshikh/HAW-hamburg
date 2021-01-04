package de.doal.haw.smoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum SmokingRequirements {
    TOBACCO,
    PAPER,
    MATCHES;

    public ArrayList<SmokingRequirements> getWhatMissing() {
        return Arrays.stream(values())
                .filter(e -> !e.equals(this))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
