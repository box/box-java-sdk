package com.box.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eclipsesource.json.JsonObject;

/**
 * The abstract base class for all resource types (files, folders, comments, collaborations, etc.) used by the API.
 *
 * <p>Every API resource has an ID and a {@link BoxAPIConnection} that it uses to communicate with the API. Some
 * resources also have an associated {@link Info} class that contains information about the resource.</p>
 */
public abstract class BoxResource {

    /**
     * @see #initResourceClassByType()
     */
    private static final Map<String, Class<? extends BoxResource>> RESOURCE_CLASS_BY_TYPE = initResourceClassByType();

    private final BoxAPIConnection api;
    private final String id;

    /**
     * Constructs a BoxResource for a resource with a given ID.
     *
     * @param  api the API connection to be used by the resource.
     * @param  id  the ID of the resource.
     */
    public BoxResource(BoxAPIConnection api, String id) {
        this.api = api;
        this.id = id;
    }

    /**
     * @return Builds {@link Map} between String {@link #getResourceType(Class)} and {@link BoxResource} type.
     */
    private static Map<String, Class<? extends BoxResource>> initResourceClassByType() {
        Map<String, Class<? extends BoxResource>> result =
                new ConcurrentHashMap<String, Class<? extends BoxResource>>();
        result.put(getResourceType(BoxFolder.class), BoxFolder.class);
        result.put(getResourceType(BoxFile.class), BoxFile.class);
        result.put(getResourceType(BoxComment.class), BoxComment.class);
        result.put(getResourceType(BoxCollaboration.class), BoxCollaboration.class);
        result.put(getResourceType(BoxTask.class), BoxTask.class);
        result.put(getResourceType(BoxTaskAssignment.class), BoxTaskAssignment.class);
        result.put(getResourceType(BoxUser.class), BoxUser.class);
        result.put(getResourceType(BoxGroup.class), BoxGroup.class);
        result.put(getResourceType(BoxGroupMembership.class), BoxGroupMembership.class);
        result.put(getResourceType(BoxEvent.class), BoxEvent.class);
        result.put(getResourceType(BoxWebHook.class), BoxWebHook.class);
        result.put(getResourceType(BoxCollection.class), BoxCollection.class);
        result.put(getResourceType(BoxDevicePin.class), BoxDevicePin.class);
        result.put(getResourceType(BoxRetentionPolicy.class), BoxRetentionPolicy.class);
        result.put(getResourceType(BoxRetentionPolicyAssignment.class), BoxRetentionPolicyAssignment.class);
        result.put(getResourceType(BoxFileVersionRetention.class), BoxFileVersionRetention.class);
        result.put(getResourceType(BoxLegalHoldPolicy.class), BoxLegalHoldPolicy.class);
        result.put(getResourceType(BoxLegalHoldAssignment.class), BoxLegalHoldAssignment.class);
        result.put(getResourceType(BoxFileVersionLegalHold.class), BoxFileVersionLegalHold.class);
        result.put(getResourceType(BoxFileUploadSession.class), BoxFileUploadSession.class);

        return Collections.unmodifiableMap(result);
    }

    /**
     * Resolves {@link BoxResourceType} for a provided {@link BoxResource} {@link Class}.
     *
     * @param clazz
     *            {@link BoxResource} type
     * @return resolved {@link BoxResourceType#value()}
     */
    public static String getResourceType(Class<? extends BoxResource> clazz) {
        BoxResourceType resource = clazz.getAnnotation(BoxResourceType.class);
        if (resource == null) {
            throw new IllegalArgumentException("Provided BoxResource type does not have @BoxResourceType annotation.");
        }
        return resource.value();
    }

    static BoxResource.Info parseInfo(BoxAPIConnection api, JsonObject jsonObject) {
        String type = jsonObject.get("type").asString();
        String id = jsonObject.get("id").asString();

        try {
            Class<? extends BoxResource> resourceClass = RESOURCE_CLASS_BY_TYPE.get(type);
            Constructor<? extends BoxResource> resourceConstructor =
                    resourceClass.getConstructor(BoxAPIConnection.class, String.class);

            Class<?> infoClass = resourceClass.getClassLoader().loadClass(resourceClass.getCanonicalName() + "$Info");
            Constructor<?> infoConstructor = infoClass.getDeclaredConstructor(resourceClass, JsonObject.class);

            BoxResource resource = resourceConstructor.newInstance(api, id);
            return (BoxResource.Info) infoConstructor.newInstance(resource, jsonObject);

        } catch (ClassNotFoundException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e) {
            throw new BoxAPIException("Can not create BoxResource.Info instance:", e);
        } catch (InvocationTargetException e) {
            throw new BoxAPIException("Can not create BoxResource.Info instance:", e);
        } catch (InstantiationException e) {
            throw new BoxAPIException("Can not create BoxResource.Info instance:", e);
        }
    }

    /**
     * Gets the API connection used by this resource.
     * @return the API connection used by this resource.
     */
    public BoxAPIConnection getAPI() {
        return this.api;
    }

    /**
     * Gets the ID of this resource.
     * @return the ID of this resource.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Indicates whether this BoxResource is equal to another BoxResource. Two BoxResources are equal if they have the
     * same type and ID.
     * @param  other the other BoxResource to compare.
     * @return       true if the type and IDs of the two resources are equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass().equals(other.getClass())) {
            BoxResource otherResource = (BoxResource) other;
            return this.getID().equals(otherResource.getID());
        }

        return false;
    }

    /**
     * Returns a hash code value for this BoxResource.
     * @return a hash code value for this BoxResource.
     */
    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

    /**
     * Contains information about a BoxResource.
     */
    public abstract class Info extends BoxJSONObject {
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
         * Gets the ID of the resource associated with this Info.
         * @return the ID of the associated resource.
         */
        public String getID() {
            return BoxResource.this.getID();
        }

        /**
         * Gets the resource associated with this Info.
         * @return the associated resource.
         */
        public abstract BoxResource getResource();
    }
}
