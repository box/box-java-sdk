package com.box.sdk;

import static com.box.sdk.internal.utils.CollectionUtils.createListFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class BoxCollaborationWhitelistIT {

    @Test
    public void createCollaborationWhitelistSucceeds() {
        final String type = "collaboration_whitelist_entry";
        final String domainName = "test14.com";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        BoxCollaborationWhitelist.Info domainWhitelist =
            BoxCollaborationWhitelist.create(api, domainName,
                BoxCollaborationWhitelist.WhitelistDirection.BOTH);

        assertThat(domainWhitelist, is(notNullValue()));
        assertEquals(domainWhitelist.getDirection(), BoxCollaborationWhitelist.WhitelistDirection.BOTH);
        assertEquals(domainWhitelist.getType(), type);
    }

    @Test
    public void getAllCollaborationWhitelistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterable<BoxCollaborationWhitelist.Info> whitelists = BoxCollaborationWhitelist.getAll(api);
        List<BoxCollaborationWhitelist.Info> whitelistList = createListFrom(whitelists);

        for (BoxCollaborationWhitelist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }

    @Test
    public void getAllCollaborationWhitelistsAdditionalParamsSucceeds() {
        final int whitelistSize = 3;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        Iterator<BoxCollaborationWhitelist.Info> iterator =
            BoxCollaborationWhitelist.getAll(api, whitelistSize).iterator();

        assertTrue(iterator.hasNext());
    }

}
