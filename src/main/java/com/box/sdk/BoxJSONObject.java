package com.box.sdk;

import java.util.HashMap;
import java.util.Map;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The abstract base class for all types that contain JSON data returned by the Box API. The most common implementation
 * of BoxJSONObject is {@link BoxResource.Info} and its subclasses. Changes made to a BoxJSONObject will be tracked
 * locally until the pending changes are sent back to Box in order to avoid unnecessary network requests.
 *
 */
public abstract class BoxJSONObject {
    /**
     * The JsonObject that contains any local pending changes. When getPendingChanges is called, this object will be
     * encoded to a JSON string.
     */
    private JsonObject pendingChanges;

    /**
     * A map of other BoxJSONObjects which will be lazily converted to a JsonObject once getPendingChanges is called.
     * This allows changes to be made to a child BoxJSONObject and still have those changes reflected in the JSON
     * string.
     */
    private final Map<String, BoxJSONObject> children;

    /**
     * Constructs an empty BoxJSONObject.
     */
    public BoxJSONObject() {
        this.children = new HashMap<String, BoxJSONObject>();
    }

    /**
     * Constructs a BoxJSONObject by decoding it from a JSON string.
     * @param  json the JSON string to decode.
     */
    public BoxJSONObject(String json) {
        this(JsonObject.readFrom(json));
    }

    /**
     * Constructs a BoxJSONObject using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxJSONObject(JsonObject jsonObject) {
        this();

        this.update(jsonObject);
    }

    /**
     * Clears any pending changes from this JSON object.
     */
    public void clearPendingChanges() {
        this.pendingChanges = null;
    }

    /**
     * Gets a JSON string containing any pending changes to this object that can be sent back to the Box API.
     * @return a JSON string containing the pending changes.
     */
    public String getPendingChanges() {
        JsonObject jsonObject = this.getPendingJSONObject();
        if (jsonObject == null) {
            return null;
        }

        return jsonObject.toString();
    }

    /**
     * Invoked with a JSON member whenever this object is updated or created from a JSON object.
     *
     * <p>Subclasses should override this method in order to parse any JSON members it knows about. This method is a
     * no-op by default.</p>
     *
     * @param member the JSON member to be parsed.
     */
    void parseJSONMember(JsonObject.Member member) { }

    /**
     * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
     * time {@link #getPendingChanges} is called.
     * @param key   the name of the field.
     * @param value the new boolean value of the field.
     */
    void addPendingChange(String key, boolean value) {
        if (this.pendingChanges == null) {
            this.pendingChanges = new JsonObject();
        }

        this.pendingChanges.set(key, value);
    }

    /**
     * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
     * time {@link #getPendingChanges} is called.
     * @param key   the name of the field.
     * @param value the new String value of the field.
     */
    void addPendingChange(String key, String value) {
        this.addPendingChange(key, JsonValue.valueOf(value));
    }

    /**
     * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
     * time {@link #getPendingChanges} is called.
     * @param key   the name of the field.
     * @param value the new long value of the field.
     */
    void addPendingChange(String key, long value) {
        this.addPendingChange(key, JsonValue.valueOf(value));
    }

    /**
     * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
     * time {@link #getPendingChanges} is called.
     * @param key   the name of the field.
     * @param value the new JsonArray value of the field.
     */
    void addPendingChange(String key, JsonArray value) {
        this.addPendingChange(key, (JsonValue) value);
    }

    void addChildObject(String fieldName, BoxJSONObject child) {
        if (child == null) {
            this.addPendingChange(fieldName, JsonValue.NULL);
        } else {
            this.children.put(fieldName, child);
        }
    }

    void removeChildObject(String fieldName) {
        this.children.remove(fieldName);
    }

    /**
     * Adds a pending field change that needs to be sent to the API. It will be included in the JSON string the next
     * time {@link #getPendingChanges} is called.
     * @param key   the name of the field.
     * @param value the JsonValue of the field.
     */
    private void addPendingChange(String key, JsonValue value) {
        if (this.pendingChanges == null) {
            this.pendingChanges = new JsonObject();
        }

        this.pendingChanges.set(key, value);
    }

    void removePendingChange(String key) {
        if (this.pendingChanges != null) {
            this.pendingChanges.remove(key);
        }
    }

    /**
     * Updates this BoxJSONObject using the information in a JSON object.
     * @param jsonObject the JSON object containing updated information.
     */
    void update(JsonObject jsonObject) {
        for (JsonObject.Member member : jsonObject) {
            if (member.getValue().isNull()) {
                continue;
            }

            this.parseJSONMember(member);
        }

        this.clearPendingChanges();
    }

    /**
     * Gets a JsonObject containing any pending changes to this object that can be sent back to the Box API.
     * @return a JsonObject containing the pending changes.
     */
    private JsonObject getPendingJSONObject() {
        for (Map.Entry<String, BoxJSONObject> entry : this.children.entrySet()) {
            BoxJSONObject child = entry.getValue();
            JsonObject jsonObject = child.getPendingJSONObject();
            if (jsonObject != null) {
                if (this.pendingChanges == null) {
                    this.pendingChanges = new JsonObject();
                }

                this.pendingChanges.set(entry.getKey(), jsonObject);
            }
        }
        return this.pendingChanges;
    }
}
