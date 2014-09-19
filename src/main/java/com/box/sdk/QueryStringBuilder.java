package com.box.sdk;

class QueryStringBuilder {
    private final StringBuilder stringBuilder;

    QueryStringBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    QueryStringBuilder addFieldsParam(String... fields) {
        StringBuilder fieldsStringBuilder = new StringBuilder();
        for (String field : fields) {
            fieldsStringBuilder.append(field);
            fieldsStringBuilder.append(",");
        }
        fieldsStringBuilder.deleteCharAt(fieldsStringBuilder.length() - 1);

        this.addParam("fields", fieldsStringBuilder.toString());
        return this;
    }

    QueryStringBuilder addParam(String key, String value) {
        if (this.stringBuilder.length() == 0) {
            this.stringBuilder.append('?');
        } else {
            this.stringBuilder.append('&');
        }

        this.stringBuilder.append(key);
        this.stringBuilder.append('=');
        this.stringBuilder.append(value);
        return this;
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }
}
