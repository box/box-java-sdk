package com.box.sdk;

/**
 * A class representing exceptions caused from deserializing errors.
 */
public class BoxDeserializationException extends RuntimeException {
    private String fieldName;
    private String fieldValue;
    private String errorMessage;

    /**
     * Initializes the BoxDeserializationException class.
     *
     * @param member the key of the json member the deserialization occurred on.
     * @param value the value of the json member the deserialization occurred on.
     * @param className the name of the class the deserialization occurred on.
     * @param e the throwable cause for the exception.
     */
    public BoxDeserializationException(String member, String value, String className, Exception e) {
        super(e);
        this.errorMessage = "Deserialization failed on " + className + " [ "  + "\"field name\": " + member + " | "
                + "\"field value\": " + value + " ]";
        this.fieldName = member;
        this.fieldValue = value;
    }

    /**
     * Retrieves the field name of the deserialization error.
     * @return field name the error occurred on.
     */
    public String getFieldName() {
        return this.fieldName;
    }

    /**
     * Retrieves the field value of the deserialization error.
     * @return field value the error occurred on.
     */
    public String getFieldValue() {
        return this.fieldValue;
    }

    /**
     * Retrieves the error message for the deserialization error.
     * @return error message.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
