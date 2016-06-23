package com.box.sdk;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.box.sdk.BoxAPIRequest.HttpMethod;
import com.box.sdk.internal.utils.CollectionUtils;
import com.box.sdk.internal.utils.CollectionUtils.Mapper;
import com.box.sdk.internal.utils.StringUtils;
import com.eclipsesource.json.JsonObject;

/**
 * Box WebHook resource.
 *
 * @author Stanislav Dvorscak
 *
 * @since 2.2.1
 *
 */
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
     * {@link URLTemplate} for {@link BoxWebHook} resource.
     */
    private static final URLTemplate WEBHOOK_URL_TEMPLATE = new URLTemplate("webhook/%s");

    /**
     * Maps a {@link Trigger} to its {@link Trigger#getValue()}.
     */
    private static final Mapper<String, BoxWebHook.Trigger> TRIGGER_TO_VALUE = new Mapper<String, BoxWebHook.Trigger>() {

        @Override
        public String map(Trigger input) {
            return input.getValue();
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
     * Adds a {@link BoxWebHook} to a provided {@link BoxItem}.
     *
     * @param target
     *            {@link BoxItem} web resource
     * @param address
     *            {@link URL} where the notification should send to
     * @param triggers
     *            events this {@link BoxWebHook} is interested in
     * @return created {@link BoxWebHook}
     *
     * @see #create(BoxItem, URL, Set)
     */
    public static BoxWebHook.Info create(BoxItem target, URL address, BoxWebHook.Trigger... triggers) {
        return create(target, address, new HashSet<>(Arrays.asList(triggers)));
    }

    /**
     * Adds a {@link BoxWebHook} to a provided {@link BoxItem}.
     *
     * @param target
     *            {@link BoxItem} web resource
     * @param address
     *            {@link URL} where the notification should send to
     * @param triggers
     *            events this {@link BoxWebHook} is interested in
     * @return created {@link BoxWebHook}
     *
     * @see #create(BoxItem, URL, Trigger...)
     */
    public static BoxWebHook.Info create(BoxItem target, URL address, Set<BoxWebHook.Trigger> triggers) {
        BoxAPIConnection api = target.getAPI();

        BoxWebHook.TargetType type = type(target);
        validateTriggers(type, triggers);

        JsonObject targetJSON = new JsonObject()
                .add(JSON_KEY_TARGET_TYPE, type.getValue())
                .add(JSON_KEY_TARGET_ID, target.getID());

        JsonObject requestJSON = new JsonObject()
                .add(JSON_KEY_TARGET, targetJSON)
                .add(JSON_KEY_ADDRESS, address.toExternalForm())
                .add(JSON_KEY_TRIGGERS, StringUtils.join(",", CollectionUtils.map(triggers, TRIGGER_TO_VALUE)));

        URL url = WEBHOOK_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, HttpMethod.POST);
        request.setBody(requestJSON.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxWebHook webHook = new BoxWebHook(api, responseJSON.get(JSON_KEY_ID).asString());
        return webHook.new Info(responseJSON);
    }

    /**
     * Resolves {@link BoxWebHook.TargetType} for a provided {@link BoxItem}.
     *
     * @param item
     *            for resolving
     * @return resolved type
     * @throws IllegalArgumentException
     *             in case of unsupported {@link BoxItem}: only {@link BoxFolder} or {@link BoxFile} is supported.
     */
    private static TargetType type(BoxItem item) {
        if (item instanceof BoxFolder) {
            return TargetType.FOLDER;
        } else if (item instanceof BoxFile) {
            return TargetType.FILE;
        } else {
            throw new IllegalArgumentException(
                    String.format("Unsupported BoxItem, expected: '%s' but it was '%s': ", "Folder or File", item.getClass().getName()));
        }
    }

    /**
     * Validates that provided {@link BoxWebHook.Trigger}-s can be applied on the provided {@link BoxWebHook.TargetType}.
     *
     * @param targetType
     *            on which target the triggers should be applied to
     * @param triggers
     *            for check
     *
     * @see #validateTrigger(TargetType, Trigger)
     */
    public static void validateTriggers(BoxWebHook.TargetType targetType, Collection<BoxWebHook.Trigger> triggers) {
        for (BoxWebHook.Trigger trigger : triggers) {
            validateTrigger(targetType, trigger);
        }
    }

    /**
     * Validates that provided {@link BoxWebHook.Trigger} can be applied on the provided {@link BoxWebHook.TargetType}.
     *
     * @param targetType
     *            on which targets the trigger should be applied to
     * @param trigger
     *            for check
     *
     * @see #validateTriggers(TargetType, Collection)
     */
    private static void validateTrigger(BoxWebHook.TargetType targetType, BoxWebHook.Trigger trigger) {
        for (BoxWebHook.TargetType type : trigger.getTypes()) {
            if (targetType.equals(type)) {
                return;
            }
        }
        throw new IllegalArgumentException(
                String.format("Provided trigger '%s' is not supported on provided target '%s'.", trigger.name(), targetType.name()));
    }

    /**
     * @return Gets information about this {@link BoxWebHook}.
     */
    public BoxWebHook.Info getInfo() {
        URL url = WEBHOOK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, HttpMethod.GET);
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
        BoxJSONRequest request = new BoxJSONRequest(getAPI(), url, HttpMethod.PUT);
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
        BoxAPIRequest request = new BoxAPIRequest(getAPI(), url, HttpMethod.DELETE);
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information for a {@link BoxWebHook} instance.
     *
     * @author Stanislav Dvorscak
     *
     */
    public class Info extends BoxResource.Info {

        /**
         * @see #getTarget()
         */
        private final Target target;

        /**
         * @see #getAddress()
         */
        private URL address;

        /**
         * @see #getTriggers()
         */
        private Set<Trigger> triggers;

        /**
         * Constructor.
         *
         * @param jsonObject
         *            a parsed JSON object
         */
        public Info(JsonObject jsonObject) {
            super(jsonObject);

            TargetType targetType = TargetType.valueOf(jsonObject.get(JSON_KEY_TARGET_TYPE).asString());
            String targetId = jsonObject.get(JSON_KEY_ID).asString();
            this.target = new Target(targetType, targetId);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxResource getResource() {
            return BoxWebHook.this;
        }

        /**
         * @return WebHook target / {@link BoxItem}.
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
            if (!Objects.equals(this.address, address)) {
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
            return this.setTriggers(new HashSet<>(Arrays.asList(triggers)));
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

            String oldValue = StringUtils.join(",", CollectionUtils.map(this.triggers, TRIGGER_TO_VALUE));
            String newValue = StringUtils.join(",", CollectionUtils.map(triggers, TRIGGER_TO_VALUE));

            if (!Objects.equals(oldValue, newValue)) {
                this.triggers = Collections.unmodifiableSet(triggers);
                this.addPendingChange(JSON_KEY_TRIGGERS, newValue);
            }

            return this;
        }

    }

    /**
     * WebHook target - file or folder.
     *
     * @author Stanislav Dvorscak
     *
     */
    public static class Target {

        /**
         * @see #getType()
         */
        private final TargetType type;

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
        public Target(TargetType type, String id) {
            this.type = type;
            this.id = id;
        }

        /**
         * @return Type of target.
         * @see TargetType
         */
        public TargetType getType() {
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
     * Supported {@link Target} types.
     *
     * @author Stanislav Dvorscak
     *
     */
    public enum TargetType {

        /**
         * WebHook for {@link BoxFolder} target type.
         */
        FOLDER("folder"),

        /**
         * WebHook for {@link BoxFile} target type.
         */
        FILE("file");

        /**
         * @see #getValue()
         */
        private final String value;

        /**
         * Constructor.
         *
         * @param value
         *            {@link #getValue()}
         */
        TargetType(String value) {
            this.value = value;
        }

        /**
         * @return String representation for {@link TargetType}.
         */
        public String getValue() {
            return this.value;
        }

    }

    /**
     * A Box related triggers.
     *
     * @author Stanislav Dvorscak
     *
     */
    public enum Trigger {

        // BoxFolder related triggers.

        /**
         * Triggered when a {@link BoxFolder} gets created.
         */
        FOLDER_CREATED("FOLDER.CREATED", TargetType.FOLDER),

        /**
         * Triggered when a {@link BoxFolder} gets copied.
         */
        FOLDER_COPIED("FOLDER.COPIED", TargetType.FOLDER),

        /**
         * Triggered when a {@link BoxFolder} gets moved.
         */
        FOLDER_MOVED("FOLDER.MOVED", TargetType.FOLDER),

        /**
         * Triggered when a {@link BoxFolder} is downloaded.
         */
        FOLDER_DOWNLOADED("FOLDER.DOWNLOADED", TargetType.FOLDER),

        /**
         * Triggered when a {@link BoxFolder} gets restored.
         */
        FOLDER_RESTORED("FOLDER.RESTORED", TargetType.FOLDER),

        /**
         * Triggered when a {@link BoxFolder} gets deleted.
         */
        FOLDER_DELETED("FOLDER.DELETED", TargetType.FOLDER),

        // BoxFile related triggers.

        /**
         * Triggered when a {@link BoxFile} gets uploaded.
         */
        FILE_UPLOADED("FILE.UPLOADED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} gets copied.
         */
        FILE_COPIED("FILE.COPIED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} gets copied.
         */
        FILE_MOVED("FILE.MOVED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} is previewed.
         */
        FILE_PREVIEWED("FILE.PREVIEWED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} is downloaded.
         */
        FILE_DOWNLOADED("FILE.DOWNLOADED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} gets locked.
         */
        FILE_LOCKED("FILE.LOCKED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} gets unlocked.
         */
        FILE_UNLOCKED("FILE.UNLOCKED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} is thrashed. Do not include file versions for now.
         */
        FILE_TRASHED("FILE.TRASHED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} gets restored.
         */
        FILE_RESTORED("FILE.RESTORED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxFile} is permanently deleted.
         */
        FILE_DELETED("FILE.DELETED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxComment} was created.
         */
        COMMENT_CREATED("COMMENT.CREATED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxComment} was updated.
         */
        COMMENT_UPDATED("COMMENT.UPDATED", TargetType.FILE),

        /**
         * Triggered when a {@link BoxComment} was deleted.
         */
        COMMENT_DELETED("COMMENT.DELETED", TargetType.FILE);

        /**
         * @see #getValue()
         */
        private final String value;

        /**
         * @see #getTypes()
         */
        private final TargetType[] types;

        /**
         * Constructor.
         *
         * @param value
         *            {@link #getValue()}
         * @param types
         *            {@link #getTypes()}
         */
        Trigger(String value, TargetType... types) {
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
         * @return Supported {@link TargetType}-s for a web-hook.
         */
        public TargetType[] getTypes() {
            return this.types;
        }

    }

}
