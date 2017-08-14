package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a device pin.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("device_pin")
public class BoxDevicePin extends BoxResource {

    /**
     * The URL template used for operation with the device pin.
     */
    public static final URLTemplate DEVICE_PIN_URL_TEMPLATE = new URLTemplate("device_pinners/%s");

    /**
     * The URL template used to get all the device pins within a given enterprise.
     */
    public static final URLTemplate ENTERPRISE_DEVICE_PINS_TEMPLATE = new URLTemplate("enterprises/%s/device_pinners");

    /**
     * Default limit of the device info entries per one response page.
     */
    private static final int DEVICES_DEFAULT_LIMIT = 100;

    /**
     * Constructs a device pin for a resource with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the resource.
     */
    public BoxDevicePin(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information about the device pin.
     * @param fields the fields to retrieve.
     * @return info about the device pin.
     */
    public Info getInfo(String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = DEVICE_PIN_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Returns iterable with all the device pins within a given enterprise.
     * Must be an enterprise admin with the manage enterprise scope to make this call.
     * @param api API used to connect the Box.
     * @param enterpriseID ID of the enterprise to get all the device pins within.
     * @param fields the optional fields to retrieve.
     * @return iterable with all the device pins within a given enterprise.
     */
    public static Iterable<BoxDevicePin.Info> getEnterpriceDevicePins(final BoxAPIConnection api, String enterpriseID,
                                                                      String ... fields) {
        return getEnterpriceDevicePins(api, enterpriseID, DEVICES_DEFAULT_LIMIT, fields);
    }

    /**
     * Returns iterable with all the device pins within a given enterprise.
     * Must be an enterprise admin with the manage enterprise scope to make this call.
     * @param api API used to connect the Box.
     * @param enterpriseID ID of the enterprise to get all the device pins within.
     * @param limit the maximum number of items per single response.
     * @param fields the optional fields to retrieve.
     * @return iterable with all the device pins within a given enterprise.
     */
    public static Iterable<BoxDevicePin.Info> getEnterpriceDevicePins(final BoxAPIConnection api, String enterpriseID,
                                                                      int limit, String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return new BoxResourceIterable<BoxDevicePin.Info>(api,
                ENTERPRISE_DEVICE_PINS_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString(), enterpriseID),
                limit) {

            @Override
            protected BoxDevicePin.Info factory(JsonObject jsonObject) {
                BoxDevicePin pin = new BoxDevicePin(api, jsonObject.get("id").asString());
                return pin.new Info(jsonObject);
            }
        };
    }

    /**
     * Deletes the device pin.
     */
    public void delete() {
        URL url = DEVICE_PIN_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a task assignment.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getOwnedBy()
         */
        private BoxUser.Info ownedBy;

        /**
         * @see #getProductName()
         */
        private String productName;

        /**
         * @see #getCreatedAt()
         */
        private Date createdAt;

        /**
         * @see #getModifiedAt()
         */
        private Date modifiedAt;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxResource getResource() {
            return BoxDevicePin.this;
        }

        /**
         * Gets ID of the user that the pin belongs to.
         * @return ID of the user that the pin belongs to.
         */
        public BoxUser.Info getOwnedBy() {
            return this.ownedBy;
        }

        /**
         * Gets the type of device being pinned.
         * @return the type of device being pinned.
         */
        public String getProductName() {
            return this.productName;
        }

        /**
         * Gets the time this pin was created.
         * @return the time this pin was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time this pin was modified.
         * @return the time this pin was modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("owned_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.ownedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.ownedBy = user.new Info(userJSON);
                    } else {
                        this.ownedBy.update(userJSON);
                    }
                } else if (memberName.equals("product_name")) {
                    this.productName = value.asString();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
