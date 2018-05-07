package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a Metadata Cascade Policy.
 */
@BoxResourceType("")
public class BoxMetadataCascadePolicy extends BoxResource{

    /**
     * Get All Metadata Cascade Policies URL.
     */
    public static final URLTemplate GET_ALL_METADATA_CASCADE_POLICIES_URL_TEMPLATE =
            new URLTemplate("metadata_cascade_policies");

    /**
     * Metadata Cascade Policies URL.
     */
    public static final URLTemplate METADATA_CASCADE_POLICIES_URL_TEMPLATE =
            new URLTemplate("metadata_cascade_polcies/%s");

    /**
     * Constructs a BoxMetadataCascadePolicy for a metadata cascade policy with a given ID.
     *
     * @param api the API connection used to make the request.
     * @param id  the ID of the metadata cascade policy.
     */
    public BoxMetadataCascadePolicy(BoxAPIConnection api, String id) { super(api, id); }






    public class Info extends BoxResource.Info {
        private BoxEnterprise ownerEnterprise;
        private BoxFolder.Info parent;
        private String scope;
        private String templateKey;

        /**
         * Constructs an empty Info object.
         */
        public Info() { super(); }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) { super(json); }

        Info(JsonObject jsonObject) { super(jsonObject); }

        /**
         * Gets the enterprise the metadata cascade policy belongs to.
         *
         * @return  the enterprise the metadata cascade policy belongs to.
         */
        public BoxEnterprise getOwnerEnterprise() {
            return this.ownerEnterprise;
        }

        /**
         * Gets the folder the metadata cascade policy is on.
         *
         * @return  the folder parent of the metadata cascade policy.
         */
        public BoxFolder.Info getParent() {
            return this.parent;
        }

        /**
         * Gets the scope of the metadata cascade policy.
         *
         * @return  the scope of the metadata cascade policy.
         */
        public String getScope() {
            return this.scope;
        }

        /**
         * Gets the template key for the metadata cascade policy.
         *
         * @return  the template key for the metadata cascade policy.
         */
        public String getTemplateKey() {
            return this.templateKey;
        }

        @Override
        public BoxMetadataCascadePolicy getResource() { return BoxMetadataCascadePolicy.this; }

//        @Override
//        protected void parseJSONMember(JsonObject.Member member) {
//            super.parseJSONMember(member);
//
//            String memberName = member.getName();
//            JsonValue value = member.getValue();
//            try {
//                if (memberName.equals("owner_enterprise")) {
//                    this.ownerEnterprise = value.asObject();
//                } else if (memberName.equals("parent")) {
//                    if()
//                }
//            }
//        }
    }
}
