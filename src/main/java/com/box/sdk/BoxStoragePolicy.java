package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;

/**
 *  Represents a BoxStoragePolicy.
 */
@BoxResourceType("storage_policy")
public class BoxStoragePolicy extends BoxResource {

    /**
     * Storage Policies URL Template.
     */
    public static final URLTemplate STORAGE_POLICY_URL_TEMPLATE = new URLTemplate("storage_policies");

    /**
     * Storage Policies URL Template.
     */
    public static final URLTemplate STORAGE_POLICY_WITH_ID_URL_TEMPLATE = new URLTemplate("storage_policies/%s");

    /**
     * The default limit of entries per response.
     */
    private static final int DEFAULT_LIMIT = 100;

    /**
     * Constructs a BoxStoragePolicy with a given ID.
     *
     * @param api the API connection to be used by the BoxStoragePolicy.
     * @param id  the ID of the BoxStoragePolicy.
     */
    public BoxStoragePolicy(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information for a Box Storage Policy with optional fields.
     *
     * @param fields the fields to retrieve.
     * @return info about this item containing only the specified fields, including storage policy.
     */
    public BoxStoragePolicy.Info getInfo(String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = STORAGE_POLICY_WITH_ID_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(),
                this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    /**
     * Returns all BoxStoragePolicy with specified fields.
     *
     * @param api    the API connection to be used by the resource.
     * @param fields the fields to retrieve.
     * @return an iterable with all the storage policies met search conditions.
     */
    public static Iterable<BoxStoragePolicy.Info> getAll(final BoxAPIConnection api, String... fields) {

        return getAll(api, DEFAULT_LIMIT, fields);
    }

    /**
     * Returns all BoxStoragePolicy with specified fields.
     *
     * @param api    the API connection to be used by the resource.
     * @param limit  the limit of items per single response. The default is 100.
     * @param fields the fields to retrieve.
     * @return an iterable with all the storage policies met search conditions.
     */
    public static Iterable<BoxStoragePolicy.Info> getAll(final BoxAPIConnection api, int limit, String... fields) {

        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }

        URL url = STORAGE_POLICY_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        return new BoxResourceIterable<BoxStoragePolicy.Info>(api, url, limit) {

            @Override
            protected BoxStoragePolicy.Info factory(JsonObject jsonObject) {
                BoxStoragePolicy storagePolicy = new BoxStoragePolicy(api, jsonObject.get("id").asString());

                return storagePolicy.new Info(jsonObject);
            }
        };
    }

    /**
     * Checks if there is already a Storage Policy Assignment and creates one if one does not exist.
     * @param userID             the ID of the user you want to assign the Storage Policy to.
     * @return information about this {@link BoxStoragePolicyAssignment}.
     */
    public BoxStoragePolicyAssignment.Info assign(String userID) {
        return BoxStoragePolicyAssignment.assign(this.getAPI(), this.getID(), userID);
    }

    /**
     * Contains information about the BoxStoragePolicy.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getStoragePolicyName()
         */
        private String storagePolicyName;

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

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxStoragePolicy getResource() {
            return BoxStoragePolicy.this;
        }

        /**
         * @return the name of the storage policy.
         */
        public String getStoragePolicyName() {
            return this.storagePolicyName;
        }

        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("name")) {
                    this.storagePolicyName = value.asString();
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
