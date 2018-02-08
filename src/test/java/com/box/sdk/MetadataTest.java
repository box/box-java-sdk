package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.text.ParseException;
import java.util.Date;

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
        Assert.assertEquals(null, m.getValue("/foo"));
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

    @Test
    @Category(UnitTest.class)
    public void getValues() {

        final String stringValue = "Q1 plans";
        final int intValue = 123456;
        final String[] arrayValue = new String[2];
        arrayValue[0] = "internal";
        arrayValue[1] = "internalEng";
        Date dateValue = new Date();
        dateValue.setTime(1507702200000L);

        String json = "{\n"
                + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
                + "    \"documentType\": \"" + stringValue + "\",\n"
                + "    \"competitiveDocument\": \"no\",\n"
                + "    \"status\": \"active\",\n"
                + "    \"deadline\": \"2017-10-10T22:10:00-08:00\",\n"
                + "    \"capacity\": " + intValue + ",\n"
                + "    \"currentState\": \"proposal\",\n"
                + "    \"$type\": \"marketingCollateral-d086c908-2498-4d3e-8a1f-01e82bfc2abe\",\n"
                + "    \"$parent\": \"file_5010739061\",\n"
                + "    \"$id\": \"2094c584-68e1-475c-a581-534a4609594e\",\n"
                + "    \"$version\": 0,\n"
                + "    \"$typeVersion\": 0,\n"
                + "    \"$template\": \"marketingCollateral\",\n"
                + "    \"$scope\": \"enterprise_12345\"\n"
                + "}";
        Metadata md = new Metadata(JsonObject.readFrom(json));

        Assert.assertEquals(stringValue, md.getValue("/documentType").asString());
        Assert.assertEquals(stringValue, md.getString("/documentType"));

        Assert.assertEquals(intValue, md.getValue("/capacity").asInt());
        Assert.assertEquals((float) intValue, md.getFloat("/capacity"), 0);

        try {
            Assert.assertEquals(dateValue, md.getDate("/deadline"));
        } catch (ParseException ex) {
            Assert.fail("Could not parse date in metadata");
        }

        for (int i = 0; i < arrayValue.length; i++) {
            Assert.assertEquals(arrayValue[i], md.getValue("/audiences").asArray().get(i).asString());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void getHandlesArrayValue() {

        String json = "{\n"
                + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
                + "    \"$type\": \"marketingCollateral-d086c908-2498-4d3e-8a1f-01e82bfc2abe\",\n"
                + "    \"$parent\": \"file_5010739061\",\n"
                + "    \"$id\": \"2094c584-68e1-475c-a581-534a4609594e\",\n"
                + "    \"$version\": 0,\n"
                + "    \"$typeVersion\": 0,\n"
                + "    \"$template\": \"marketingCollateral\",\n"
                + "    \"$scope\": \"enterprise_12345\"\n"
                + "}";
        Metadata md = new Metadata(JsonObject.readFrom(json));

        String value = md.get("/audiences");
        Assert.assertEquals("[\"internal\",\"internalEng\"]", value);
    }
}
