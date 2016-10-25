package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

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
}
