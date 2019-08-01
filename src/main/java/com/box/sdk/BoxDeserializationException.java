package com.box.sdk;

import java.text.ParseException;

/**
 *
 */
public class BoxDeserializationException extends RuntimeException {
    public String fieldName;
    public String fieldValue;
    public String errorMessage;

    public BoxDeserializationException(String member, String value, String className, Exception e) {
        super(e);
        this.errorMessage = "Deserialization failed on " + className + " [ "  + "\"field name\": " + member + " | " +
                "\"field value\": " + value + " ]";
        this.fieldName = member;
        this.fieldValue = value;
    }
}
