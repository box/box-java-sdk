package com.box.sdk;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;


/**
 * The MetadataTemplate class represents the Box metadata template object.
 * Templates allow the metadata service to provide a multitude of services,
 * such as pre-defining sets of key:value pairs or schema enforcement on specific fields.
 *
 * @see <a href="https://docs.box.com/reference#metadata-templates">Box metadata templates</a>
 */
public class MetadataTemplate extends BoxJSONObject {

    /**
     * @see #getMetadataTemplate(BoxAPIConnection)
     */
    private static final URLTemplate METADATA_TEMPLATE_URL_TEMPLATE
        = new URLTemplate("metadata_templates/%s/%s/schema");

    /**
     * @see #createMetadataTemplate(BoxAPIConnection, String, String, String, boolean, List)
     */
    private static final URLTemplate METADATA_TEMPLATE_SCHEMA_URL_TEMPLATE
        = new URLTemplate("metadata_templates/schema");

    /**
     * @see #getEnterpriseMetadataTemplates(String, int, BoxAPIConnection, String...)
     */
    private static final URLTemplate ENTERPRISE_METADATA_URL_TEMPLATE = new URLTemplate("metadata_templates/%s");

    /**
     * Default metadata type to be used in query.
     */
    private static final String DEFAULT_METADATA_TYPE = "properties";

    /**
     * Global metadata scope. Used by default if the metadata type is "properties".
     */
    private static final String GLOBAL_METADATA_SCOPE = "global";

    /**
     * Enterprise metadata scope. Used by default if the metadata type is not "properties".
     */
    private static final String ENTERPRISE_METADATA_SCOPE = "enterprise";

    /**
     * Default number of entries per page.
     */
    private static final int DEFAULT_ENTRIES_LIMIT = 100;

    /**
     * @see #getTemplateKey()
     */
    private String templateKey;

    /**
     * @see #getScope()
     */
    private String scope;

    /**
     * @see #getDisplayName()
     */
    private String displayName;

    /**
     * @see #getIsHidden()
     */
    private Boolean isHidden;

    /**
     * @see #getFields()
     */
    private List<Field> fields;

    /**
     * Constructs an empty metadata template.
     */
    public MetadataTemplate() {
        super();
    }

    /**
     * Constructs a metadata template from a JSON string.
     * @param json the json encoded metadate template.
     */
    public MetadataTemplate(String json) {
        super(json);
    }

    /**
     * Constructs a metadate template from a JSON object.
     * @param jsonObject the json encoded metadate template.
     */
    MetadataTemplate(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the unique template key to identify the metadata template.
     * @return the unique template key to identify the metadata template.
     */
    public String getTemplateKey() {
        return this.templateKey;
    }

    /**
     * Gets the metadata template scope.
     * @return the metadata template scope.
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * Gets the displayed metadata template name.
     * @return the displayed metadata template name.
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Gets is the metadata template hidden.
     * @return is the metadata template hidden.
     */
    public Boolean getIsHidden() {
        return this.isHidden;
    }

    /**
     * Gets the iterable with all fields the metadata template contains.
     * @return the iterable with all fields the metadata template contains.
     */
    public List<Field> getFields() {
        return this.fields;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("templateKey")) {
            this.templateKey = value.asString();
        } else if (memberName.equals("scope")) {
            this.scope = value.asString();
        } else if (memberName.equals("displayName")) {
            this.displayName = value.asString();
        } else if (memberName.equals("hidden")) {
            this.isHidden = value.asBoolean();
        } else if (memberName.equals("fields")) {
            this.fields = new ArrayList<Field>();
            for (JsonValue field: value.asArray()) {
                this.fields.add(new Field(field.asObject()));
            }
        }
    }

    /**
     * Creates new metadata template.
     * @param api the API connection to be used.
     * @param scope the scope of the object.
     * @param templateKey a unique identifier for the template.
     * @param displayName the display name of the field.
     * @param hidden whether this template is hidden in the UI.
     * @param fields the ordered set of fields for the template
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate createMetadataTemplate(BoxAPIConnection api, String scope, String templateKey,
            String displayName, boolean hidden, List<Field> fields) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("scope", scope);
        jsonObject.add("displayName", displayName);
        jsonObject.add("hidden", hidden);

        if (templateKey != null) {
            jsonObject.add("templateKey", templateKey);
        }

        JsonArray fieldsArray = new JsonArray();
        if (fields != null && !fields.isEmpty()) {
            for (Field field : fields) {
                JsonObject fieldObj = getFieldJsonObject(field);

                fieldsArray.add(fieldObj);
            }

            jsonObject.add("fields", fieldsArray);
        }

        URL url = METADATA_TEMPLATE_SCHEMA_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(jsonObject.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        return new MetadataTemplate(responseJSON);
    }

    /**
     * Gets the JsonObject representation of the given field object.
     * @param field represents a template field
     * @return the json object
     */
    private static JsonObject getFieldJsonObject(Field field) {
        JsonObject fieldObj = new JsonObject();
        fieldObj.add("type", field.getType());
        fieldObj.add("key", field.getKey());
        fieldObj.add("displayName", field.getDisplayName());

        String fieldDesc = field.getDescription();
        if (fieldDesc != null) {
            fieldObj.add("description", field.getDescription());
        }

        Boolean fieldIsHidden = field.getIsHidden();
        if (fieldIsHidden != null) {
            fieldObj.add("hidden", field.getIsHidden());
        }

        JsonArray array = new JsonArray();
        List<String> options = field.getOptions();
        if (options != null && !options.isEmpty()) {
            for (String option : options) {
                JsonObject optionObj = new JsonObject();
                optionObj.add("key", option);

                array.add(optionObj);
            }
            fieldObj.add("options", array);
        }

        return fieldObj;
    }

    /**
     * Updates the schema of an existing metadata template.
     *
     * @param api the API connection to be used
     * @param scope the scope of the object
     * @param template Unique identifier of the template
     * @param fieldOperations the fields that needs to be updated / added in the template
     * @return the updated metadata template
     */
    public static MetadataTemplate updateMetadataTemplate(BoxAPIConnection api, String scope, String template,
            List<FieldOperation> fieldOperations) {

        JsonArray array = new JsonArray();

        for (FieldOperation fieldOperation : fieldOperations) {
            JsonObject jsonObject = getFieldOperationJsonObject(fieldOperation);
            array.add(jsonObject);
        }

        QueryStringBuilder builder = new QueryStringBuilder();
        URL url = METADATA_TEMPLATE_URL_TEMPLATE.build(api.getBaseURL(), scope, template);
        BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
        request.setBody(array.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJson = JsonObject.readFrom(response.getJSON());

        return new MetadataTemplate(responseJson);
    }

    /**
     * Gets the JsonObject representation of the Field Operation.
     * @param fieldOperation represents the template update operation
     * @return the json object
     */
    private static JsonObject getFieldOperationJsonObject(FieldOperation fieldOperation) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("op", fieldOperation.getOp().toString());

        String fieldKey = fieldOperation.getFieldKey();
        if (fieldKey != null) {
            jsonObject.add("fieldKey", fieldKey);
        }

        Field field = fieldOperation.getData();
        if (field != null) {
            JsonObject fieldObj = new JsonObject();

            String type = field.getType();
            if (type != null) {
                fieldObj.add("type", type);
            }

            String key = field.getKey();
            if (key != null) {
                fieldObj.add("key", key);
            }

            String displayName = field.getDisplayName();
            if (displayName != null) {
                fieldObj.add("displayName", displayName);
            }

            String description = field.getDescription();
            if (description != null) {
                fieldObj.add("description", description);
            }

            Boolean hidden = field.getIsHidden();
            if (hidden != null) {
                fieldObj.add("hidden", hidden);
            }

            List<String> options = field.getOptions();
            if (options != null) {
                JsonArray array = new JsonArray();
                for (String option: options) {
                    JsonObject optionObj = new JsonObject();
                    optionObj.add("key", option);

                    array.add(optionObj);
                }

                fieldObj.add("options", array);
            }

            jsonObject.add("data", fieldObj);
        }

        List<String> fieldKeys = fieldOperation.getFieldKeys();
        if (fieldKeys != null) {
            jsonObject.add("fieldKeys", getJsonArray(fieldKeys));
        }

        List<String> enumOptionKeys = fieldOperation.getEnumOptionKeys();
        if (enumOptionKeys != null) {
            jsonObject.add("enumOptionKeys", getJsonArray(enumOptionKeys));
        }

        return jsonObject;
    }

    /**
     * Gets the Json Array representation of the given list of strings.
     * @param keys List of strings
     * @return the JsonArray represents the list of keys
     */
    private static JsonArray getJsonArray(List<String> keys) {
        JsonArray array = new JsonArray();
        for (String key : keys) {
            array.add(key);
        }

        return array;
    }

    /**
     * Gets the metadata template of properties.
     * @param api the API connection to be used.
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(BoxAPIConnection api) {
        return getMetadataTemplate(api, DEFAULT_METADATA_TYPE);
    }

    /**
     * Gets the metadata template of specified template type.
     * @param api the API connection to be used.
     * @param templateName the metadata template type name.
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(BoxAPIConnection api, String templateName) {
        String scope = scopeBasedOnType(templateName);
        return getMetadataTemplate(api, templateName, scope);
    }

    /**
     * Gets the metadata template of specified template type.
     * @param api the API connection to be used.
     * @param templateName the metadata template type name.
     * @param scope the metadata template scope (global or enterprise).
     * @param fields the fields to retrieve.
     * @return the metadata template returned from the server.
     */
    public static MetadataTemplate getMetadataTemplate(
            BoxAPIConnection api, String templateName, String scope, String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = METADATA_TEMPLATE_URL_TEMPLATE.buildWithQuery(
                api.getBaseURL(), builder.toString(), scope, templateName);
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new MetadataTemplate(response.getJSON());
    }

    /**
     * Returns all metadata templates within a user's enterprise.
     * @param api the API connection to be used.
     * @param fields the fields to retrieve.
     * @return the metadata template returned from the server.
     */
    public static Iterable<MetadataTemplate> getEnterpriseMetadataTemplates(BoxAPIConnection api, String ... fields) {
        return getEnterpriseMetadataTemplates(ENTERPRISE_METADATA_SCOPE, api, fields);
    }

    /**
     * Returns all metadata templates within a user's scope. Currently only the enterprise scope is supported.
     * @param scope the scope of the metadata templates.
     * @param api the API connection to be used.
     * @param fields the fields to retrieve.
     * @return the metadata template returned from the server.
     */
    public static Iterable<MetadataTemplate> getEnterpriseMetadataTemplates(
            String scope, BoxAPIConnection api, String ... fields) {
        return getEnterpriseMetadataTemplates(ENTERPRISE_METADATA_SCOPE, DEFAULT_ENTRIES_LIMIT, api, fields);
    }

    /**
     * Returns all metadata templates within a user's scope. Currently only the enterprise scope is supported.
     * @param scope the scope of the metadata templates.
     * @param limit maximum number of entries per response.
     * @param api the API connection to be used.
     * @param fields the fields to retrieve.
     * @return the metadata template returned from the server.
     */
    public static Iterable<MetadataTemplate> getEnterpriseMetadataTemplates(
            String scope, int limit, BoxAPIConnection api, String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new BoxResourceIterable<MetadataTemplate>(
                api, ENTERPRISE_METADATA_URL_TEMPLATE.buildWithQuery(
                        api.getBaseURL(), builder.toString(), scope), limit) {

            @Override
            protected MetadataTemplate factory(JsonObject jsonObject) {
                return new MetadataTemplate(jsonObject);
            }
        };
    }

    /**
     * Determines the metadata scope based on type.
     * @param typeName type of the metadata.
     * @return scope of the metadata.
     */
    private static String scopeBasedOnType(String typeName) {
        return typeName.equals(DEFAULT_METADATA_TYPE) ? GLOBAL_METADATA_SCOPE : ENTERPRISE_METADATA_SCOPE;
    }

    /**
     * Class contains information about the metadata template field.
     */
    public static class Field extends BoxJSONObject {

        /**
         * @see #getType()
         */
        private String type;

        /**
         * @see #getKey()
         */
        private String key;

        /**
         * @see #getDisplayName()
         */
        private String displayName;

        /**
         * @see #getIsHidden()
         */
        private Boolean isHidden;

        /**
         * @see #getDescription()
         */
        private String description;

        /**
         * @see #getOptions()
         */
        private List<String> options;

        /**
         * Constructs an empty metadata template.
         */
        public Field() {
            super();
        }

        /**
         * Constructs a metadate template field from a JSON string.
         * @param json the json encoded metadate template field.
         */
        public Field(String json) {
            super(json);
        }

        /**
         * Constructs a metadate template field from a JSON object.
         * @param jsonObject the json encoded metadate template field.
         */
        Field(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the data type of the field's value.
         * @return the data type of the field's value.
         */
        public String getType() {
            return this.type;
        }

        /**
         * Sets the data type of the field's value.
         * @param type the data type of the field's value.
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Gets the key of the field.
         * @return the key of the field.
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Sets the key of the field.
         * @param key the key of the field.
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * Gets the display name of the field.
         * @return the display name of the field.
         */
        public String getDisplayName() {
            return this.displayName;
        }

        /**
         * Sets the display name of the field.
         * @param displayName the display name of the field.
         */
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Gets is metadata template field hidden.
         * @return is metadata template field hidden.
         */
        public Boolean getIsHidden() {
            return this.isHidden;
        }

        /**
         * Sets is metadata template field hidden.
         * @param isHidden is metadata template field hidden?
         */
        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

        /**
         * Gets the description of the field.
         * @return the description of the field.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description of the field.
         * @param description the description of the field.
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Gets list of possible options for enum type of the field.
         * @return list of possible options for enum type of the field.
         */
        public List<String> getOptions() {
            return this.options;
        }

        /**
         * Sets list of possible options for enum type of the field.
         * @param options list of possible options for enum type of the field.
         */
        public void setOptions(List<String> options) {
            this.options = options;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            JsonValue value = member.getValue();
            String memberName = member.getName();
            if (memberName.equals("type")) {
                this.type = value.asString();
            } else if (memberName.equals("key")) {
                this.key = value.asString();
            } else if (memberName.equals("displayName")) {
                this.displayName = value.asString();
            } else if (memberName.equals("hidden")) {
                this.isHidden = value.asBoolean();
            } else if (memberName.equals("description")) {
                this.description = value.asString();
            } else if (memberName.equals("options")) {
                this.options = new ArrayList<String>();
                for (JsonValue key: value.asArray()) {
                    this.options.add(key.asObject().get("key").asString());
                }
            }
        }
    }

    /**
     * Posssible operations that can be performed in a Metadata template.
     *  <ul>
     *      <li>Add an enum option</li>
     *      <li>Add a field</li>
     *      <li>Edit a field</li>
     *      <li>Edit template</li>
     *      <li>Reorder the enum option</li>
     *      <li>Reorder the field list</li>
     *  </ul>
     */
    public static class FieldOperation extends BoxJSONObject {

        private Operation op;
        private Field data;
        private String fieldKey;
        private List<String> fieldKeys;
        private List<String> enumOptionKeys;

        /**
         * Constructs an empty FieldOperation.
         */
        public FieldOperation() {
            super();
        }

        /**
         * Constructs a Field operation from a JSON string.
         * @param json the json encoded metadate template field.
         */
        public FieldOperation(String json) {
            super(json);
        }

        /**
         * Constructs a Field operation from a JSON object.
         * @param jsonObject the json encoded metadate template field.
         */
        FieldOperation(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the operation.
         * @return the operation
         */
        public Operation getOp() {
            return this.op;
        }

        /**
         * Gets the data associated with the operation.
         * @return the field object representing the data
         */
        public Field getData() {
            return this.data;
        }

        /**
         * Gets the field key.
         * @return the field key
         */
        public String getFieldKey() {
            return this.fieldKey;
        }

        /**
         * Gets the list of field keys.
         * @return the list of Strings
         */
        public List<String> getFieldKeys() {
            return this.fieldKeys;
        }

        /**
         * Gets the list of keys of the Enum options.
         * @return the list of Strings
         */
        public List<String> getEnumOptionKeys() {
            return this.enumOptionKeys;
        }

        /**
         * Sets the operation.
         * @param op the operation
         */
        public void setOp(Operation op) {
            this.op = op;
        }

        /**
         * Sets the data.
         * @param data the Field object representing the data
         */
        public void setData(Field data) {
            this.data = data;
        }

        /**
         * Sets the field key.
         * @param fieldKey the key of the field
         */
        public void setFieldKey(String fieldKey) {
            this.fieldKey = fieldKey;
        }

        /**
         * Sets the list of the field keys.
         * @param fieldKeys the list of strings
         */
        public void setFieldKeys(List<String> fieldKeys) {
            this.fieldKeys = fieldKeys;
        }

        /**
         * Sets the list of the enum option keys.
         * @param enumOptionKeys the list of Strings
         */
        public void setEnumOptionKeys(List<String> enumOptionKeys) {
            this.enumOptionKeys = enumOptionKeys;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            JsonValue value = member.getValue();
            String memberName = member.getName();
            if (memberName.equals("op")) {
                this.op = Operation.valueOf(value.asString());
            } else if (memberName.equals("data")) {
                this.data = new Field(value.asObject());
            } else if (memberName.equals("fieldKey")) {
                this.fieldKey = value.asString();
            } else if (memberName.equals("fieldKeys")) {
                if (this.fieldKeys == null) {
                    this.fieldKeys = new ArrayList<String>();
                } else {
                    this.fieldKeys.clear();
                }

                JsonArray array = value.asArray();
                for (JsonValue jsonValue: array) {
                    this.fieldKeys.add(jsonValue.asString());
                }
            } else if (memberName.equals("enumOptionKeys")) {
                if (this.enumOptionKeys == null) {
                    this.enumOptionKeys = new ArrayList<String>();
                } else {
                    this.enumOptionKeys.clear();
                }

                JsonArray array = value.asArray();
                for (JsonValue jsonValue: array) {
                    this.enumOptionKeys.add(jsonValue.asString());
                }
            }
        }
    }

    /**
     * Possible template operations.
     */
    public enum Operation {

        /**
         * Adds an enum option at the end of the enum option list for the specified field.
         */
        addEnumOption,

        /**
         * Adds a field at the end of the field list for the template.
         */
        addField,

        /**
         * Edits any number of the base properties of a field: displayName, hidden, description.
         */
        editField,

        /**
         * Edits any number of the base properties of a template: displayName, hidden.
         */
        editTemplate,

        /**
         * Reorders the enum option list to match the requested enum option list.
         */
        reorderEnumOptions,

        /**
         * Reorders the field list to match the requested field list.
         */
        reorderFields
    }
}
