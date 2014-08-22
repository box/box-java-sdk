package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxAPIConnectionTest {
    @Test
    @Category(UnitTest.class)
    public void canRefreshWhenGivenRefreshToken() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());

        assertTrue(api.canRefresh());
    }

    @Test
    @Category(UnitTest.class)
    public void needsRefreshWhenTokenHasExpired() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(-1);

        assertTrue(api.needsRefresh());
    }

    @Test
    @Category(UnitTest.class)
    public void doesNotNeedRefreshWhenTokenHasNotExpired() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(Long.MAX_VALUE);

        assertFalse(api.needsRefresh());
    }

    @Test
    @Category(UnitTest.class)
    public void doesNotNeedRefreshWhenExpiresIsZero() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(0);

        assertFalse(api.needsRefresh());
    }

    @Test
    @Category(IntegrationTest.class)
    public void refreshSucceeds() {
        final String originalAuthToken = TestConfig.getAuthToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAuthToken, originalRefreshToken);

        api.refresh();

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertNotEquals(originalRefreshToken, actualRefreshToken);
        assertNotEquals(originalAuthToken, actualAccessToken);
    }

    @Test
    @Category(IntegrationTest.class)
    public void refreshesWhenGetAccessTokenIsCalledAndTokenHasExpired() {
        final String originalAuthToken = TestConfig.getAuthToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAuthToken, originalRefreshToken);
        api.setExpires(-1);

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertNotEquals(originalRefreshToken, actualRefreshToken);
        assertNotEquals(originalAuthToken, actualAccessToken);
    }

    @Test
    @Category(IntegrationTest.class)
    public void doesNotRefreshWhenGetAccessTokenIsCalledAndTokenHasNotExpired() {
        final String originalAuthToken = TestConfig.getAuthToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAuthToken, originalRefreshToken);
        api.setExpires(Long.MAX_VALUE);

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertEquals(originalRefreshToken, actualRefreshToken);
        assertEquals(originalAuthToken, actualAccessToken);
    }
}
