package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class MetadataTest {

    @Test
    @Category(UnitTest.class)
    public void testConstructor() {
        Metadata m = new Metadata();
        Assert.assertEquals("{}", m.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testCopyConstructor() {
        Metadata m1 = new Metadata().add("/foo", "bar");
        Metadata m2 = new Metadata(m1);
        Assert.assertEquals("{\"foo\":\"bar\"}", m2.toString());
    }

    @Test
    @Category(UnitTest.class)
    public void testAdd() {
        Metadata m = new Metadata().add("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("add", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testReplace() {
        Metadata m = new Metadata().replace("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("replace", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testTest() {
        Metadata m = new Metadata().test("/foo", "bar");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("test", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
        Assert.assertEquals("bar", op.get("value").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testRemove() {
        Metadata m = new Metadata().remove("/foo");
        JsonArray operations = JsonArray.readFrom(m.getPatch());
        Assert.assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        Assert.assertEquals("remove", op.get("op").asString());
        Assert.assertEquals("/foo", op.get("path").asString());
    }

    @Test
    @Category(UnitTest.class)
    public void testInvalidGet() {
        Metadata m = new Metadata();
        Assert.assertEquals(null, m.get("/foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    @Category(UnitTest.class)
    public void testNullPath() {
        new Metadata().add(null, "value");
    }

    @Test(expected = IllegalArgumentException.class)
    @Category(UnitTest.class)
    public void testInvalidPath() {
        new Metadata().add("key", "value");
    }

    @Test
    @Category(UnitTest.class)
    public void testMetaProperties() {
        String json = "{\"$id\":\"123\",\"$type\":\"my type\",\"$parent\":\"456\"}";
        Metadata m = new Metadata(JsonObject.readFrom(json));
        Assert.assertEquals("123", m.getID());
        Assert.assertEquals("my type", m.getTypeName());
        Assert.assertEquals("456", m.getParentID());
    }

    @Test
    @Category(UnitTest.class)
    public void testMissingMetaProperties() {
        Metadata m = new Metadata();
        Assert.assertEquals(null, m.getID());
        Assert.assertEquals(null, m.getTypeName());
        Assert.assertEquals(null, m.getParentID());
    }
}
