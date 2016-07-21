package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.box.sdk.internal.utils.CollectionUtils;
import com.box.sdk.internal.utils.CollectionUtils.Mapper;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Box WebHook resource.
 *
 * @since 2.2.1
 *
 */
@BoxResourceType("webhook")
public class BoxWebHook extends BoxResource {

    /**
     * JSON Key for {@link BoxWebHook} {@link #getID()}.
     */
    private static final String JSON_KEY_ID = "id";

    /**
     * JSON Key for {@link BoxWebHook.Info#getTarget()}.
     */
    private static final String JSON_KEY_TARGET = "target";

    /**
     * JSON Key for {@link BoxWebHook.Target#getType()}.
     */
    private static final String JSON_KEY_TARGET_TYPE = "type";

    /**
     * JSON Key for {@link BoxWebHook.Target#getId()}.
     */
    private static final String JSON_KEY_TARGET_ID = "id";

    /**
     * JSON Key for {@link BoxWebHook.Info#getAddress()}.
     */
    private static final String JSON_KEY_ADDRESS = "address";

    /**
     * JSON Key for {@link BoxWebHook.Info#getTriggers()}.
     */
    private static final String JSON_KEY_TRIGGERS = "triggers";

    /**
     * {@link URLTemplate} for {@link BoxWebHook}s resource.
     */
    private static final URLTemplate WEBHOOKS_URL_TEMPLATE = new URLTemplate("webhooks");
    /**
     * {@link URLTemplate} for single {@link BoxWebHook} resource.
     */
    private static final URLTemplate WEBHOOK_URL_TEMPLATE = new URLTemplate("webhooks/%s");

    /**
     * Maps a {@link Trigger} to its {@link Trigger#getValue()}.
     */
    private static final Mapper<String, BoxWebHook.Trigger> TRIGGER_TO_VALUE = new Mapper<String, Trigger>() {

        @Override
        public String map(Trigger input) {
            return input.getValue();
        }

    };

    private static final Mapper<Trigger, JsonValue> JSON_VALUE_TO_TRIGGER = new Mapper<Trigger, JsonValue>() {
        @Override
        public Trigger map(JsonValue value) {
            return Trigger.fromValue(value.asString());
        }
    };

    /**
     * Constructor.
     *
     * @param api
     *            {@link #getAPI()}
     * @param id
     *            {@link #getID()}
     */
    public BoxWebHook(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Adds a {@link BoxWebHook} to a provided {@link BoxResource}.
     *
     * @param target
     *            {@link BoxResource} web resource
     * @param address
     *            {@link URL} where the notification should send to
     * @param triggers
     *            events this {@link BoxWebHook} is interested in
     * @return created {@link BoxWebHook}
     *
     * @see #create(BoxResource, URL, Set)
     */
    public static BoxWebHook.Info create(BoxResource target, URL address, BoxWebHook.Trigger... triggers) {
        return create(target, address, new HashSet<Trigger>(Arrays.asList(triggers)));
    }

    /**
     * Adds a {@link BoxWebHook} to a provided {@link BoxResource}.
     *
     * @param target
     *            {@link BoxResource} web resource
     * @param address
     *            {@link URL} where the notification should send to
     * @param triggers
     *            events this {@link BoxWebHook} is interested in
     * @return created {@link BoxWebHook}
     *
     * @see #create(BoxResource, URL, Trigger...)
     */
    public static BoxWebHook.Info create(BoxResource target, URL address, Set<BoxWebHook.Trigger> triggers) {
        BoxAPIConnection api = target.getAPI();

        String type = BoxResource.getResourceType(target.getClass());
        validateTriggers(type, triggers);

        JsonObject targetJSON = new JsonObject()
                .add(JSON_KEY_TARGET_TYPE, type)
                .add(JSON_KEY_TARGET_ID, target.getID());

        JsonObject requestJSON = new JsonObject()
                .add(JSON_KEY_TARGET, targetJSON)
                .add(JSON_KEY_ADDRESS, address.toExternalForm())
                .add(JSON_KEY_TRIGGERS, toJsonArray(CollectionUtils.map(triggers, TRIGGER_TO_VALUE)));

        URL url = WEBHOOKS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxWebHook webHook = new BoxWebHook(api, responseJSON.get(JSON_KEY_ID).asString());
        return webHook.new Info(responseJSON);
    }

    /**
     * Helper function to create JsonArray from collection.
     *
     * @param values collection of values to convert to JsonArray
     * @return JsonArray with collection values
     */
    private static JsonArray toJsonArray(Collection<String> values) {
        JsonArray array = new JsonArray();
        for (String value : values) {
            array.add(value);
        }
        return array;

    }

    /**
     * Returns iterator over all {@link BoxWebHook}-s.
     *
     * @param api
     *            the API connection to be used by the resource
     * @return existing {@link BoxWebHook.Info}-s
     */
    public static Iterable<BoxWebHook.Info> all(final BoxAPIConnection api) {
        return new BoxResourceIterable<BoxWebHook.Info>(api, WEBHOOKS_URL_TEMPLATE.build(api.getBaseURL()), 64) {

            @Override
            protected BoxWebHook.Info factory(JsonObject jsonObject) {
                BoxWebHook webHook = new BoxWebHook(api, jsonObject.get("id").asString());
                return webHook.new Info(jsonObject);
            }

        };
    }

    /**
     * Validates that provided {@link BoxWebHook.Trigger}-s can be applied on the provided {@link BoxResourceType}.
     *
     * @param targetType
     *            on which target the triggers should be applied to
     * @param triggers
     *            for check
     *
     * @see #validateTrigger(String, Trigger)
     */
    public static void validateTriggers(String targetType, Collection<BoxWebHook.Trigger> triggers) {
        for (BoxWebHook.Trigger trigger : triggers) {
            validateTrigger(targetType, trigger);
        }
    }

    /**
     * Validates that provided {@link BoxWebHook.Trigger} can be applied on the provided {@link BoxResourceType}.
     *
     * @param targetType
     *            on which targets the trigger should be applied to
     * @param trigger
     *            for check
     *
     * @see #validateTriggers(String, Collection)
     */
    private static void validateTrigger(String targetType, BoxWebHook.Trigger trigger) {
        for (String type : trigger.getTypes()) {
            if (targetType.equals(type)) {
                return;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Provided trigger '%s' is not supported on provided target '%s'.", trigger.name(), targetType));
    }

    /**
     * @return Gets information about this {@link BoxWebHook}.
     */
    public BoxWebHook.Info getInfo() {
        URL url = WEBHOOK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Updates {@link BoxWebHook} information.
     *
     * @param info
     *            new state
     */
    public void updateInfo(BoxWebHook.Info info) {
        URL url = WEBHOOK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Deletes this webhook.
     */
    public void delete() {
        URL url = WEBHOOK_URL_TEMPLATE.build(getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information for a {@link BoxWebHook} instance.
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getTarget()
         */
        private Target target;

        /**
         * @see #getAddress()
         */
        private URL address;

        /**
         * @see #getTriggers()
         */
        private Set<Trigger> triggers;

        /**
         * Constructs an Info object with current target.
         */
        public Info() {
            super();
            this.target = BoxWebHook.this.getInfo().getTarget();
        }

        /**
         * Constructor.
         *
         * @param jsonObject
         *            a parsed JSON object
         */
        public Info(JsonObject jsonObject) {
            super(jsonObject);

            if (jsonObject.get(JSON_KEY_TARGET) != null) {
                JsonObject targetObject = jsonObject.get(JSON_KEY_TARGET).asObject();
                String targetType = targetObject.get(JSON_KEY_TARGET_TYPE).asString();
                String targetId = targetObject.get(JSON_KEY_TARGET_ID).asString();
                this.target = new Target(targetType, targetId);
            }

            if (jsonObject.get(JSON_KEY_TRIGGERS) != null) {
                this.triggers = new HashSet<Trigger>(
                        CollectionUtils.map(jsonObject.get(JSON_KEY_TRIGGERS).asArray().values(), JSON_VALUE_TO_TRIGGER)
                );
            }
            if (jsonObject.get(JSON_KEY_ADDRESS) != null) {
                try {
                    this.address = new URL(jsonObject.get(JSON_KEY_ADDRESS).asString());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxWebHook getResource() {
            return BoxWebHook.this;
        }

        /**
         * @return WebHook target / {@link BoxResource}.
         */
        public Target getTarget() {
            return this.target;
        }

        /**
         * @return {@link URL} where the notification should send to.
         */
        public URL getAddress() {
            return this.address;
        }

        /**
         * Setter for {@link #getAddress()}.
         *
         * @param address
         *            {@link #getAddress()}
         * @return itself
         */
        public Info setAddress(URL address) {
            if (address == null) {
                throw new IllegalArgumentException("Address cannot be null");
            }
            if (this.address == null || !this.address.equals(address)) {
                this.address = address;
                this.addPendingChange(JSON_KEY_ADDRESS, address.toExternalForm());
            }

            return this;
        }

        /**
         * @return Events this webhook is interested in.
         */
        public Set<Trigger> getTriggers() {
            return this.triggers;
        }

        /**
         * Sets {@link #getTriggers()}.
         *
         * @param triggers
         *            {@link #getTriggers()}
         * @return itself
         */
        public Info setTriggers(BoxWebHook.Trigger... triggers) {
            return this.setTriggers(new HashSet<Trigger>(Arrays.asList(triggers)));
        }

        /**
         * Setter for {@link #getTriggers()}.
         *
         * @param triggers
         *            {@link #getTriggers()}
         * @return itself
         */
        public Info setTriggers(Set<BoxWebHook.Trigger> triggers) {
            validateTriggers(this.target.getType(), triggers);

            JsonArray oldValue;
            if (this.triggers != null) {
                oldValue = toJsonArray(CollectionUtils.map(this.triggers, TRIGGER_TO_VALUE));
            } else {
                oldValue = null;
            }
            JsonArray newValue = toJsonArray(CollectionUtils.map(triggers, TRIGGER_TO_VALUE));

            if (!newValue.equals(oldValue)) {
                this.triggers = Collections.unmodifiableSet(triggers);
                this.addPendingChange(JSON_KEY_TRIGGERS, newValue);
            }

            return this;
        }

    }

    /**
     * WebHook target - file or folder.
     */
    public static class Target {

        /**
         * @see #getType()
         */
        private final String type;

        /**
         * @see #getId()
         */
        private final String id;

        /**
         * Constructor.
         *
         * @param type
         *            {@link #getType()}
         * @param id
         *            {@link #getId()}
         */
        public Target(String type, String id) {
            this.type = type;
            this.id = id;
        }

        /**
         * @return Type of target.
         * @see BoxResourceType
         */
        public String getType() {
            return this.type;
        }

        /**
         * @return {@link BoxResource#getID()}
         */
        public String getId() {
            return this.id;
        }

    }

    /**
     * A Box related triggers.
     */
    public enum Trigger {

        // BoxFolder related triggers.

        /**
         * Triggered when a {@link BoxFolder} gets created.
         */
        FOLDER_CREATED("FOLDER.CREATED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFolder} gets copied.
         */
        FOLDER_COPIED("FOLDER.COPIED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFolder} gets moved.
         */
        FOLDER_MOVED("FOLDER.MOVED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFolder} is downloaded.
         */
        FOLDER_DOWNLOADED("FOLDER.DOWNLOADED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFolder} gets restored.
         */
        FOLDER_RESTORED("FOLDER.RESTORED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFolder} gets deleted.
         */
        FOLDER_DELETED("FOLDER.DELETED", BoxResource.getResourceType(BoxFolder.class)),

        // BoxFile related triggers.

        /**
         * Triggered when a {@link BoxFile} gets uploaded.
         */
        FILE_UPLOADED("FILE.UPLOADED", BoxResource.getResourceType(BoxFolder.class)),

        /**
         * Triggered when a {@link BoxFile} gets copied.
         */
        FILE_COPIED("FILE.COPIED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} gets copied.
         */
        FILE_MOVED("FILE.MOVED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} is previewed.
         */
        FILE_PREVIEWED("FILE.PREVIEWED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} is downloaded.
         */
        FILE_DOWNLOADED("FILE.DOWNLOADED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} gets locked.
         */
        FILE_LOCKED("FILE.LOCKED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} gets unlocked.
         */
        FILE_UNLOCKED("FILE.UNLOCKED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} is thrashed. Do not include file versions for now.
         */
        FILE_TRASHED("FILE.TRASHED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} gets restored.
         */
        FILE_RESTORED("FILE.RESTORED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxFile} is permanently deleted.
         */
        FILE_DELETED("FILE.DELETED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxComment} was created.
         */
        COMMENT_CREATED("COMMENT.CREATED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxComment} was updated.
         */
        COMMENT_UPDATED("COMMENT.UPDATED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class)),

        /**
         * Triggered when a {@link BoxComment} was deleted.
         */
        COMMENT_DELETED("COMMENT.DELETED",
                BoxResource.getResourceType(BoxFolder.class), BoxResource.getResourceType(BoxFile.class));

        /**
         * @see #getValue()
         */
        private final String value;

        /**
         * @see #getTypes()
         */
        private final String[] types;

        /**
         * Constructor.
         *
         * @param value
         *            {@link #getValue()}
         * @param types
         *            {@link #getTypes()}
         */
        Trigger(String value, String... types) {
            this.value = value;
            this.types = types;
        }

        /**
         * @return {@link String} representation for {@link Trigger}.
         */
        public String getValue() {
            return this.value;
        }

        /**
         * @param value value to get the Trigger enum value for
         * @return Trigger for given value
         */
        public static Trigger fromValue(String value) {
            for (Trigger trigger : Trigger.values()) {
                if (trigger.getValue().equals(value)) {
                    return trigger;
                }
            }
            throw new IllegalArgumentException("No Trigger for value: " + value);
        }

        /**
         * @return Supported types for a web-hook.
         */
        public String[] getTypes() {
            return this.types;
        }

    }

}
