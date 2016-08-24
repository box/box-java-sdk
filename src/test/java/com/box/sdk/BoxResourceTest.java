package com.box.sdk;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.eclipsesource.json.JsonObject;

/**
 * A {@link BoxResource} related unit tests.
 */
public class BoxResourceTest {

    /**
     * {@link BoxResourceType} by {@link BoxResource} {@link Class}.
     */
    private final Map<Class<? extends BoxResource>, String> resourceTypeByClass;

    /**
     * Constructor.
     */
    public BoxResourceTest() {
        Map<Class<? extends BoxResource>, String> resourceTypeByClass =
                new HashMap<Class<? extends BoxResource>, String>();
        resourceTypeByClass.put(BoxFolder.class, "folder");
        resourceTypeByClass.put(BoxFile.class, "file");
        resourceTypeByClass.put(BoxComment.class, "comment");
        resourceTypeByClass.put(BoxCollaboration.class, "collaboration");
        resourceTypeByClass.put(BoxTask.class, "task");
        resourceTypeByClass.put(BoxTaskAssignment.class, "task_assignment");
        resourceTypeByClass.put(BoxUser.class, "user");
        resourceTypeByClass.put(BoxGroup.class, "group");
        resourceTypeByClass.put(BoxGroupMembership.class, "group_membership");
        resourceTypeByClass.put(BoxEvent.class, "event");
        resourceTypeByClass.put(BoxWebHook.class, "webhook");
        this.resourceTypeByClass = resourceTypeByClass;
    }

    /**
     * Unit tests for {@link BoxResource#getResourceType(Class)}.
     */
    @Test
    public void testGetType() {
        for (Map.Entry<Class<? extends BoxResource>, String> resourceTypeByClassEntry
                : this.resourceTypeByClass.entrySet()) {
            Assert.assertEquals(
                 resourceTypeByClassEntry.getValue(),
                 BoxResource.getResourceType(resourceTypeByClassEntry.getKey())
            );
        }
    }

    /**
     * Unit tests for {@link BoxResource#getResourceType(Class)} / Illegal resource type (e.g.: abstract).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeUnknown() {
        BoxResource.getResourceType(BoxResource.class);
    }

    /**
     * Unit tests for {@link BoxResource#parseInfo(BoxAPIConnection, JsonObject)}.
     */
    @Test
    public void testParseInfo() {
        for (Map.Entry<Class<? extends BoxResource>, String> resourceTypeByClassEntry
                : this.resourceTypeByClass.entrySet()) {

            String type = resourceTypeByClassEntry.getValue();
            Class<? extends BoxResource> clazz = resourceTypeByClassEntry.getKey();

            if (BoxEvent.class.isAssignableFrom(clazz)) {
                continue;
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.set("type", type);
            jsonObject.set("id", "id");

            BoxResource.Info resource = BoxResource.parseInfo(null, jsonObject);
            Assert.assertNotNull(String.format("Resource Info for '%s' type can not be null!", type), resource);
            Assert.assertEquals(type, BoxResource.getResourceType(resource.getResource().getClass()));
        }
    }

}
