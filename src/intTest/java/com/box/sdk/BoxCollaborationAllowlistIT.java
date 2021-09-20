package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoxCollaborationAllowlistIT {
    private static final String DOMAIN_NAME = "test14.com";

    @BeforeClass
    public static void beforeClass() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        for (BoxCollaborationAllowlist.Info info : BoxCollaborationAllowlist.getAll(api)) {
            info.getResource().delete();
        }
    }

    @Test
    public void createCollaborationAllowlistSucceeds() {
        final String type = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationAllowlist.Info domainAllowlist = BoxCollaborationAllowlist.create(
            api,
            "createCollaborationAllowlistSucceeds." + DOMAIN_NAME,
            BoxCollaborationAllowlist.AllowlistDirection.BOTH
        );

        assertThat(domainAllowlist, is(notNullValue()));
        assertEquals(domainAllowlist.getDirection(), BoxCollaborationAllowlist.AllowlistDirection.BOTH);
        assertEquals(domainAllowlist.getType(), type);
    }

    @Test
    public void getAllCollaborationAllowlistsSucceeds() {
        final String whitelistType = "collaboration_whitelist_entry";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxCollaborationAllowlist.create(
            api,
            "getAllCollaborationAllowlistsSucceeds." + DOMAIN_NAME,
            BoxCollaborationAllowlist.AllowlistDirection.BOTH
        );

        Iterable<BoxCollaborationAllowlist.Info> whitelists = BoxCollaborationAllowlist.getAll(api);
        List<BoxCollaborationAllowlist.Info> whitelistList = newArrayList(whitelists);

        assertThat(whitelistList, is(not(Matchers.<BoxCollaborationAllowlist.Info>empty())));
        for (BoxCollaborationAllowlist.Info whitelistInfo : whitelistList) {
            assertThat(whitelistInfo, is(notNullValue()));
            assertEquals(whitelistInfo.getType(), whitelistType);
        }
    }

    @Test
    public void getAllCollaborationAllowlistsAdditionalParamsSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());

        BoxCollaborationAllowlist.create(
            api,
            "getAllCollaborationAllowlistsAdditionalParamsSucceeds." + DOMAIN_NAME,
            BoxCollaborationAllowlist.AllowlistDirection.BOTH
        );

        Iterable<BoxCollaborationAllowlist.Info> whitelists = BoxCollaborationAllowlist.getAll(api, 10);
        List<BoxCollaborationAllowlist.Info> whitelistList = newArrayList(whitelists);
        assertThat(whitelistList, is(not(Matchers.<BoxCollaborationAllowlist.Info>empty())));
    }

    private <T> List<T> newArrayList(Iterable<T> iterable) {
        List<T> result = new ArrayList<>();
        for (T i : iterable) {
            result.add(i);
        }
        return result;
    }
}
