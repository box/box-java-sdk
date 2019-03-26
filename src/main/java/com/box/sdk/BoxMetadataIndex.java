package com.box.sdk;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import com.box.sdk.internal.utils.Parsers;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Base class for Metadata Query
 */
public class BoxMetadataIndex extends BoxResource {

    public static final URLTemplate METADATA_INDEX_URL_TEMPLATE
            = new URLTemplate("metadata_template_indexes");

    public BoxMetadataIndex(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static Iterable<BoxMetadataIndex.Info> getAll(final BoxAPIConnection api,
                                                         String templateID, String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("template_id", templateID);

        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new BoxResourceIterable<Info>(api, METADATA_INDEX_URL_TEMPLATE
            .buildWithQuery(api.getBaseURL(), builder.toString()), 100) {
            @Override
            protected BoxMetadataIndex.Info factory(JsonObject jsonObject) {
                BoxMetadataIndex metadataIndex =
                        new BoxMetadataIndex(api, jsonObject.get("id").asString());

                return metadataIndex.new Info(jsonObject);
            }
        };
    }

    public static BoxMetadataIndex create(final BoxAPIConnection api, String templateID, String templateKey,
                                               String displayName, List<Field> fields) {

        JsonObject templateBody = new JsonObject();
        templateBody.add("type", "metadata_template")
                      .add("id", templateID)

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("key", templateKey)
                  .add("displayName", displayName)
                  .add("template", templateBody);

        JsonArray fieldsArray = new JsonArray();
        if (fields != null && !fields.isEmpty()) {
            for (Field field : fields) {
                JsonObject fieldObject = getFieldJsonObject(field);

                fieldsArray.add(fieldObject);
            }

            jsonObject.add("field", fieldsArray);
        }

        URL url = METADATA_INDEX_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(jsonObject);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxMetadataIndex index = new BoxMetadataIndex(api, responseJSON.get("id").asString());
        return new index.new Info(responseJSON);

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
     * Class contains information about the metadata template field.
     */
    public static class Field extends BoxJSONObject {

        /**
         * @see #getID()
         */
        private String id;

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
         * @see #getOptionsObject()
         */
        private List<MetadataTemplate.Option> options;

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
         * Gets the ID of the template field.
         * @return the template field ID.
         */
        public String getID() {
            return this.id;
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
            if (this.options == null) {
                return null;
            }
            List<String> optionsList = new ArrayList<String>();
            for (MetadataTemplate.Option option : this.options) {
                optionsList.add(option.getKey());
            }
            return optionsList;
        }

        /**
         * Gets list of possible options for options type of the field.
         * @return list of possible options for option type of the field.
         */
        public List<MetadataTemplate.Option> getOptionsObjects() {
            return this.options;
        }

        /**
         * Sets list of possible options for enum type of the field.
         * @param options list of possible options for enum type of the field.
         */
        public void setOptions(List<String> options) {
            if (options == null) {
                this.options = null;
            }
            List<MetadataTemplate.Option> optionList = new ArrayList<MetadataTemplate.Option>();
            for (String key : options) {
                JsonObject optionObject = new JsonObject();
                optionObject.add("key", key);
                MetadataTemplate.Option newOption = new MetadataTemplate.Option(optionObject);
                optionList.add(newOption);
            }
            this.options = optionList;
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
                this.options = new ArrayList<MetadataTemplate.Option>();
                for (JsonValue option : value.asArray()) {
                    this.options.add(new MetadataTemplate.Option(option.asObject()));
                }
            } else if (memberName.equals("id")) {
                this.id = value.asString();
            }
        }
    }

    public class Info extends BoxResource.Info {
        private MetadataTemplate template;
        private String key;
        private String displayName;

        public Info() {
            super();
        }

        public Info(String json) {
            super(json);
        }

        public Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        public MetadataTemplate getTemplate() {
            return this.template;
        }

        public String getKey() {
            return this.key;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        @Override
        public BoxMetadataIndex getResource() {
            return BoxMetadataIndex.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("template")) {
                this.template = new MetadataTemplate(value.asObject());
            } else if (memberName.equals("key")) {
                this.key = value.asString();
            } else if (memberName.equals("displayName")) {
                this.displayName = value.asString();
            }
        }

    }
}
