package com.box.sdk;

import com.box.sdk.internal.utils.JsonUtils;
import com.eclipsesource.json.JsonObject;

public class BoxAIExtractMetadataTemplate extends BoxJSONObject {
    /**
     * The type of object this class represents.
     */
    public static final String TYPE = "metadata_template";
    /**
     * The scope of the metadata template that can either be global or enterprise.
     */
    private String scope;
    /**
     * The template key of the metadata template.
     */
    private String templateKey;

    /**
     * Constructs a BoxAIExtractMetadataTemplate object with a given scope and template key.
     * @param templateKey the template key of the metadata template.
     * @param scope the scope of the metadata template.
     */
    public BoxAIExtractMetadataTemplate(String templateKey, String scope) {
        this.templateKey = templateKey;
        this.scope = scope;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        if (memberName.equals("scope")) {
            this.scope = member.getValue().asString();
        } else if (memberName.equals("template_key")) {
            this.templateKey = member.getValue().asString();
        }
    }

    public JsonObject getJSONObject() {
        JsonObject jsonObject = new JsonObject();
        JsonUtils.addIfNotNull(jsonObject, "type", TYPE);
        JsonUtils.addIfNotNull(jsonObject, "scope", this.scope);
        JsonUtils.addIfNotNull(jsonObject, "template_key", this.templateKey);
        return jsonObject;
    }

    public String getScope() {
        return this.scope;
    }

    public String getTemplateKey() {
        return this.templateKey;
    }
}
