package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.Collection;
import java.net.URL;
import com.eclipsesource.json.ParseException;

@BoxResourceType("zone")
public class BoxStoragePolicy extends BoxResource{

    /**
     * Storage Policy URL Template;
     */
    public static final URLTemplate STORAGE_POLICIES_URL_TEMPLATE = new URLTemplate("storage_policies/%s");

    /**
     * Constructs a BoxStoragePolicy with a given ID.
     * @param api the API connection to be used by the BoxStoragePolicy.
     * @param id  the ID of the BoxStoragePolicy.
     */
    public BoxStoragePolicy(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static Collection<BoxStoragePolicy> getAll(BoxAPIConnection api) {
        URL url = STORAGE_POLICIES_URL_TEMPLATE.build(api.getBaseURL());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
    }

    /**
     * Gets information for a Box Storage Policy with optional fields.
     * @param fields the fields to retrieve.
     * @return info about this item containing only the specified fields, including storage policy.
     */
    public BoxStoragePolicy.Info getInfo(String... fields) {
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
        URL url = STORAGE_POLICIES_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
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
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
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
                assert false: "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
