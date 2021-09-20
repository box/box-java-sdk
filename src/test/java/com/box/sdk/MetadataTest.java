package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

public class MetadataTest {

    @Test
    public void testConstructor() {
        Metadata m = new Metadata();
        assertEquals("{}", m.toString());
    }

    @Test
    public void testCopyConstructor() {
        Metadata m1 = new Metadata().add("/foo", "bar");
        Metadata m2 = new Metadata(m1);
        assertEquals("{\"foo\":\"bar\"}", m2.toString());
    }

    @Test
    public void testAdd() {
        Metadata m = new Metadata().add("/foo", "bar");
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("add", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
    }

    @Test
    public void testReplace() {
        Metadata m = new Metadata().replace("/foo", "bar");
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("replace", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
    }

    @Test
    public void testReplaceWithMultiSelect() {
        List<String> valueList = new ArrayList<>();
        valueList.add("bar");
        valueList.add("qux");
        Metadata m = new Metadata().replace("/foo", valueList);
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("replace", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("[\"bar\",\"qux\"]", op.get("value").toString());
    }

    @Test
    public void testTest() {
        Metadata m = new Metadata().test("/foo", "bar");
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("test", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals("bar", op.get("value").asString());
    }

    @Test
    public void testMultiSelect() {
        String expectedList = "[\"public test 1\",\"public test 2\",\"public test 3\"]";
        List<String> list = new ArrayList<>();
        list.add("public test 1");
        list.add("public test 2");
        list.add("public test 3");
        Metadata m = new Metadata().test("/foo", list);
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        JsonObject op = operations.get(0).asObject();
        assertEquals("test", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
        assertEquals(expectedList, op.get("value").toString());
    }

    @Test
    public void testRemove() {
        Metadata m = new Metadata().remove("/foo");
        JsonArray operations = Json.parse(m.getPatch()).asArray();
        assertEquals(1, operations.size());
        JsonObject op = operations.get(0).asObject();
        assertEquals("remove", op.get("op").asString());
        assertEquals("/foo", op.get("path").asString());
    }

    @Test
    public void testInvalidGet() {
        Metadata m = new Metadata();
        assertNull(m.getValue("/foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPath() {
        new Metadata().add(null, "value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPath() {
        new Metadata().add("key", "value");
    }

    @Test
    public void testMetaProperties() {
        String json = "{\"$id\":\"123\",\"$type\":\"my type\",\"$parent\":\"456\"}";
        Metadata m = new Metadata(Json.parse(json).asObject());
        assertEquals("123", m.getID());
        assertEquals("my type", m.getTypeName());
        assertEquals("456", m.getParentID());
    }

    @Test
    public void testMissingMetaProperties() {
        Metadata m = new Metadata();
        assertNull(m.getID());
        assertNull(m.getTypeName());
        assertNull(m.getParentID());
    }

    @Test
    public void testGetValues() {
        // Run test with various Date formats.
        this.getValues("\"2017-10-10T22:10:00-08:00\"", 1507702200000L);
        this.getValues("\"2017-10-10T22:10:00.000-08:00\"", 1507702200000L);
        this.getValues("\"2017-10-10T22:10:00.123-08:00\"", 1507702200123L);
        this.getValues("\"2017-10-10T22:10:00.100-08:00\"", 1507702200100L);
    }

    public void getValues(String dateString, Long dateLong) {

        final String stringValue = "Q1 plans";
        final int intValue = 123456;
        final String[] arrayValue = new String[2];
        arrayValue[0] = "internal";
        arrayValue[1] = "internalEng";
        Date dateValue = new Date();
        dateValue.setTime(dateLong);

        String json = "{\n"
            + "    \"audiences\": [\"internal\", \"internalEng\"],\n"
            + "    \"documentType\": \"" + stringValue + "\",\n"
            + "    \"competitiveDocument\": \"no\",\n"
            + "    \"status\": \"active\",\n"
            + "    \"deadline\": " + dateString + ",\n"
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
        Metadata md = new Metadata(Json.parse(json).asObject());

        assertEquals(stringValue, md.getValue("/documentType").asString());
        assertEquals(stringValue, md.getString("/documentType"));

        assertEquals(intValue, md.getValue("/capacity").asInt());
        assertEquals(intValue, md.getDouble("/capacity"), 0);

        try {
            assertEquals(dateValue, md.getDate("/deadline"));
        } catch (ParseException ex) {
            fail("Could not parse date in metadata");
        }

        for (int i = 0; i < arrayValue.length; i++) {
            assertEquals(arrayValue[i], md.getValue("/audiences").asArray().get(i).asString());
        }
    }

    @Test
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
        Metadata md = new Metadata(Json.parse(json).asObject());

        List<String> audiences = this.audiencesAsList(md.getValue("/audiences"));
        assertThat(audiences, contains("internal", "internalEng"));
    }

    private List<String> audiencesAsList(JsonValue value) {
        JsonArray jsonValues = value.asArray();
        List<String> audiences = new ArrayList<>();
        for (JsonValue jsonValue : jsonValues) {
            audiences.add(jsonValue.asString());
        }
        return audiences;
    }
}
