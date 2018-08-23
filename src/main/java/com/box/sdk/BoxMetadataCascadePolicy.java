package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a Metadata Cascade Policy.
 */
@BoxResourceType("metadata_cascade_policy")
public class BoxMetadataCascadePolicy extends BoxResource {

    /**
     * Get All Metadata Cascade Policies URL.
     */
    public static final URLTemplate GET_ALL_METADATA_CASCADE_POLICIES_URL_TEMPLATE =
            new URLTemplate("metadata_cascade_policies");

    /**
     * Metadata Cascade Policies URL.
     */
    public static final URLTemplate METADATA_CASCADE_POLICIES_URL_TEMPLATE =
            new URLTemplate("metadata_cascade_policies/%s");

    /**
     * Force Metadata Cascade Policies URL.
     */
    public static final URLTemplate FORCE_METADATA_CASCADE_POLICIES_URL_TEMPLATE =
            new URLTemplate("metadata_cascade_policies/%s/apply");

    private static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxMetadataCascadePolicy for a metadata cascade policy with a given ID.
     *
     * @param api the API connection used to make the request.
     * @param id  the ID of the metadata cascade policy.
     */
    public BoxMetadataCascadePolicy(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Retrieves list of Box Metadata Cascade Policies that belong to your Enterprise as an Iterable.
     *
     * @param api      the API connection to be used by the resource.
     * @param folderID the ID of the folder to retrieve cascade policies for.
     * @param fields   optional fields to retrieve for cascade policies.
     * @return the Iterable of Box Metadata Cascade Policies in your enterprise.
     */
    public static Iterable<BoxMetadataCascadePolicy.Info> getAll(final BoxAPIConnection api,
                                                                 String folderID, String ... fields) {
        return getAll(api, folderID, null, DEFAULT_LIMIT, fields);
    }

    /**
     * Retrieves list of Box Metadata Cascade Policies that belong to your Enterprise as an Iterable.
     *
     * @param api               the API connection to be used by the resource.
     * @param folderID          the ID of the folder to retrieve cascade policies for.
     * @param ownerEnterpriseID the ID of the enterprise to retrieve Metadata Cascade Policies for.
     * @param limit             the number of entries for cascade policies to retrieve.
     * @param fields            optional fields to retrieve for cascade policies.
     * @return the Iterable of Box Metadata Cascade Policies in your enterprise.
     */
    public static Iterable<BoxMetadataCascadePolicy.Info> getAll(final BoxAPIConnection api,
                                                                 String folderID, String ownerEnterpriseID, int limit,
                                                                 String... fields) {

        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("folder_id", folderID);
        if (ownerEnterpriseID != null) {
            builder.appendParam("owner_enterprise_id", ownerEnterpriseID);
        }
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new BoxResourceIterable<Info>(api, GET_ALL_METADATA_CASCADE_POLICIES_URL_TEMPLATE
                .buildWithQuery(api.getBaseURL(), builder.toString()), limit) {
            @Override
            protected BoxMetadataCascadePolicy.Info factory(JsonObject jsonObject) {
                BoxMetadataCascadePolicy cascadePolicy =
                        new BoxMetadataCascadePolicy(api, jsonObject.get("id").asString());

                return cascadePolicy.new Info(jsonObject);
            }
        };
    }

    /**
     * Returns the information for a specific BoxMetadataCascadePolicy.
     *
     * @param fields the fields to retrieve.
     * @return the information about this metadata cascade policy.
     */
    public BoxMetadataCascadePolicy.Info getInfo(String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = METADATA_CASCADE_POLICIES_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(),
                builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Creates a new Metadata Cascade Policy on a folder.
     *
     * @param api         the API connection to be used by the resource.
     * @param folderID    the ID of the folder to create a metadata cascade policy on.
     * @param scope       the scope of the metadata cascade policy.
     * @param templateKey the key of the template.
     * @return information about the Metadata Cascade Policy.
     */
    public static BoxMetadataCascadePolicy.Info create(final BoxAPIConnection api, String folderID, String scope,
                                                       String templateKey) {
        URL url = GET_ALL_METADATA_CASCADE_POLICIES_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("folder_id", folderID)
                .add("scope", scope)
                .add("templateKey", templateKey);
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxMetadataCascadePolicy createdMetadataCascadePolicy = new BoxMetadataCascadePolicy(api,
                responseJSON.get("id").asString());
        return createdMetadataCascadePolicy.new Info(responseJSON);
    }

    /**
     * If a policy already exists on a folder, this will apply that policy to all existing files and sub folders within
     * the target folder.
     *
     * @param conflictResolution the desired behavior for conflict-resolution. Set to either none or overwrite.
     */
    public void forceApply(String conflictResolution) {

        URL url = FORCE_METADATA_CASCADE_POLICIES_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("conflict_resolution", conflictResolution);
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
    }

    /**
     * Deletes the metadata cascade policy.
     */
    public void delete() {
        URL url = METADATA_CASCADE_POLICIES_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
    }

    /**
     * Contains information about a BoxMetadataCascadePolicy.
     */
    public class Info extends BoxResource.Info {
        private BoxEnterprise ownerEnterprise;
        private BoxFolder.Info parent;
        private String scope;
        private String templateKey;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the enterprise the metadata cascade policy belongs to.
         *
         * @return the enterprise the metadata cascade policy belongs to.
         */
        public BoxEnterprise getOwnerEnterprise() {
            return this.ownerEnterprise;
        }

        /**
         * Gets the folder the metadata cascade policy is on.
         *
         * @return the folder parent of the metadata cascade policy.
         */
        public BoxFolder.Info getParent() {
            return this.parent;
        }

        /**
         * Gets the scope of the metadata cascade policy.
         *
         * @return the scope of the metadata cascade policy.
         */
        public String getScope() {
            return this.scope;
        }

        /**
         * Gets the template key for the metadata cascade policy.
         *
         * @return the template key for the metadata cascade policy.
         */
        public String getTemplateKey() {
            return this.templateKey;
        }

        @Override
        public BoxMetadataCascadePolicy getResource() {
            return BoxMetadataCascadePolicy.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("owner_enterprise")) {
                    JsonObject jsonObject = value.asObject();
                    this.ownerEnterprise = new BoxEnterprise(jsonObject);
                } else if (memberName.equals("parent")) {
                    JsonObject parentJSON = value.asObject();
                    if (this.parent == null) {
                        String parentID = parentJSON.get("id").asString();
                        BoxFolder folder = new BoxFolder(getAPI(), parentID);
                        this.parent = folder.new Info(parentJSON);
                    } else {
                        this.parent.update(parentJSON);
                    }
                } else if (memberName.equals("scope")) {
                    this.scope = value.asString();
                } else if (memberName.equals("templateKey")) {
                    this.templateKey = value.asString();
                }
            } catch (Exception e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
