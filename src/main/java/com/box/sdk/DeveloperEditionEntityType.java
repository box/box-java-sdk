package com.box.sdk;

/**
 * Enum to represent the JWT entity type for BoxDeveloperEditionAPIConnection.
 */
public enum DeveloperEditionEntityType {

    /**
     * Represents the entity type enterprise.
     */
    ENTERPRISE("enterprise"),

    /**
     * Represents the entity type user.
     */
    USER("user");

    private final String value;

    /**
     * @param value
     */
    DeveloperEditionEntityType(String value) {
        this.value = value;
    }

    /**
     * To get the string value for the enum type.
     *
     * @return The string value for the enum type
     */
    @Override
    public String toString() {
        return this.value;
    }
}
