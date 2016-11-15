package com.box.sdk;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxDevicePin} related unit tests.
 */
public class BoxDevicePinTest {

    /**
     * Unit test for {@link BoxDevicePin#getInfo(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/device_pinners/0?fields=owned_by%2Cproduct_name",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        BoxDevicePin pin = new BoxDevicePin(api, "0");
        pin.getInfo("owned_by", "product_name");
    }

    /**
     * Unit test for {@link BoxDevicePin#getInfo(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "788804";
        final String userID = "222276603";
        final String userName = "Ted Blosser";
        final String userLogin = "ted+boxworks2@box.com";
        final String productName = "iPad";
        final Date createdAt = BoxDateFormat.parse("2014-09-23T22:56:18-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2014-09-23T22:56:18-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"device_pinner\",\n"
                + "    \"id\": \"788804\",\n"
                + "    \"owned_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"222276603\",\n"
                + "        \"name\": \"Ted Blosser\",\n"
                + "        \"login\": \"ted+boxworks2@box.com\"\n"
                + "    },\n"
                + "    \"product_name\": \"iPad\",\n"
                + "    \"created_at\": \"2014-09-23T22:56:18-07:00\",\n"
                + "    \"modified_at\": \"2014-09-23T22:56:18-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxDevicePin pin = new BoxDevicePin(api, "788804");
        BoxDevicePin.Info info = pin.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(userID, info.getOwnedBy().getID());
        Assert.assertEquals(userName, info.getOwnedBy().getName());
        Assert.assertEquals(userLogin, info.getOwnedBy().getLogin());
        Assert.assertEquals(productName, info.getProductName());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
    }

    /**
     * Unit test for {@link BoxDevicePin#getEnterpriceDevicePins(BoxAPIConnection, String, String...)}.
     */
    @Test(expected = NoSuchElementException.class)
    @Category(UnitTest.class)
    public void testGetEnterpriseDevicePinsSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/enterprises/0/device_pinners?fields=owned_by%2Cproduct_name&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[]}";

                    }
                };
            }
        });

        Iterator<BoxDevicePin.Info> iterator =
                BoxDevicePin.getEnterpriceDevicePins(api, "0", "owned_by", "product_name").iterator();
        iterator.next();
    }

    /**
     * Unit test for {@link BoxDevicePin#getEnterpriceDevicePins(BoxAPIConnection, String, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetEnterpriseDevicePinsParseAllFieldsCorrectly() {
        final String firstEntryID = "788804";
        final String firstEntryUserID = "222276603";
        final String firstEntryUserName = "Ted Blosser";
        final String firstEntryUserLogin = "ted+boxworks2@box.com";
        final String firstEntryProductName = "iPad";
        final String secondEntryID = "1003086";
        final String secondEntryUserID = "222276604";
        final String secondEntryUserName = "Alison Wonderland";
        final String secondEntryUserLogin = "alison+wonderland2@box.com";
        final String secondEntryProductName = "iPhone";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"device_pinner\",\n"
                + "            \"id\": \"788804\",\n"
                + "            \"owned_by\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"222276603\",\n"
                + "                \"name\": \"Ted Blosser\",\n"
                + "                \"login\": \"ted+boxworks2@box.com\"\n"
                + "            },\n"
                + "            \"product_name\": \"iPad\"\n"
                + "        },\n"
                + "\n"
                + "        {\n"
                + "            \"type\": \"device_pinner\",\n"
                + "            \"id\": \"1003086\",\n"
                + "            \"owned_by\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"222276604\",\n"
                + "                \"name\": \"Alison Wonderland\",\n"
                + "                \"login\": \"alison+wonderland2@box.com\"\n"
                + "            },\n"
                + "            \"product_name\": \"iPhone\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"limit\": 100,\n"
                + "    \"order\": [\n"
                + "        {\n"
                + "            \"by\": \"id\",\n"
                + "            \"direction\": \"ASC\"\n"
                + "        }\n"
                + "    ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxDevicePin.Info> iterator = BoxDevicePin.getEnterpriceDevicePins(api, "0").iterator();
        BoxDevicePin.Info info = iterator.next();
        Assert.assertEquals(firstEntryID, info.getID());
        Assert.assertEquals(firstEntryUserID, info.getOwnedBy().getID());
        Assert.assertEquals(firstEntryUserName, info.getOwnedBy().getName());
        Assert.assertEquals(firstEntryUserLogin, info.getOwnedBy().getLogin());
        Assert.assertEquals(firstEntryProductName, info.getProductName());
        info = iterator.next();
        Assert.assertEquals(secondEntryID, info.getID());
        Assert.assertEquals(secondEntryUserID, info.getOwnedBy().getID());
        Assert.assertEquals(secondEntryUserName, info.getOwnedBy().getName());
        Assert.assertEquals(secondEntryUserLogin, info.getOwnedBy().getLogin());
        Assert.assertEquals(secondEntryProductName, info.getProductName());
        Assert.assertEquals(false, iterator.hasNext());
    }

    /**
     * Unit test for {@link BoxDevicePin#delete()}.
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/device_pinners/0", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";

                    }
                };
            }
        });

        BoxDevicePin pin = new BoxDevicePin(api, "0");
        pin.delete();

    }
}
