package edu.usm.healthsystem.model.familyplanning;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum representing different types of contraceptive methods.
 * Each method includes a descriptive label.
 */
@Getter
public enum ContraceptiveMethod {

    /** Oral contraceptive pill method. */
    PILL("Oral Contraceptive Pill"),

    /** Injectable contraceptive method. */
    INJECTION("Injectable Contraceptive");

    /** Human-readable description of the contraceptive method. */
    private final String description;

    /**
     * Constructs a ContraceptiveMethod enum with a given description.
     *
     * @param description A string describing the contraceptive method.
     */
    ContraceptiveMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String[] getMethods() {
        return Arrays.stream(values())
                .map(ContraceptiveMethod::getDescription)
                .toArray(String[]::new);
    }

}