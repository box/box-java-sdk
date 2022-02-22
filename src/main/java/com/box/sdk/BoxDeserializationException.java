package com.box.sdk;

/**
 * A class representing exceptions caused from deserializing errors.
 */
public class BoxDeserializationException extends RuntimeException {
    static final long serialVersionUID = 4266400750306343595L;
    private final String fieldName;
    private final String fieldValue;

    /**
     * Initializes the BoxDeserializationException class.
     *
     * @param member the key of the json member the deserialization occurred on.
     * @param value  the value of the json member the deserialization occurred on.
     * @param e      the throwable cause for the exception.
     */
    public BoxDeserializationException(String member, String value, Exception e) {
        super(constructExceptionMessage(member, value), e);
        this.fieldName = member;
        this.fieldValue = value;
    }

    /**
     * Private helper function to construct the exception message for the deserialization error.
     *
     * @param member the field member to include in the exception message.
     * @param value  the field value to include in the exception message.
     * @return the constructed exception message.
     */
    private static String constructExceptionMessage(String member, String value) {
        return "Deserialization failed on: [ " + "\"field name\": " + member + " | "
            + "\"field value\": " + value + " ]";
    }

    /**
     * Retrieves the field name of the deserialization error.
     *
     * @return field name the error occurred on.
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * Retrieves the field value of the deserialization error.
     *
     * @return field value the error occurred on.
     */
    public String getFieldValue() {
        return this.fieldValue;
    }
}
