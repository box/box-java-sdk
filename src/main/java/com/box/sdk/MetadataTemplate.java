package com.box.sdk;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    public class Field extends BoxJSONObject {

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
         * Gets the key of the field.
         * @return the key of the field.
         */
        public String getKey() {
            return this.key;
        }

        /**
         * Gets the display name of the field.
         * @return the display name of the field.
         */
        public String getDisplayName() {
            return this.displayName;
        }

        /**
         * Gets is metadata template field hidden.
         * @return is metadata template field hidden.
         */
        public Boolean getIsHidden() {
            return this.isHidden;
        }

        /**
         * Gets the description of the field.
         * @return the description of the field.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Gets list of possible options for enum type of the field.
         * @return list of possible options for enum type of the field.
         */
        public List<String> getOptions() {
            return this.options;
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

}
