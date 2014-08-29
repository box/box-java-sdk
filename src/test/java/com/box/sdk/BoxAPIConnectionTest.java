package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxAPIConnectionTest {
    @Test
    @Category(UnitTest.class)
    public void canRefreshWhenGivenRefreshToken() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());

        assertThat(api.canRefresh(), is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void needsRefreshWhenTokenHasExpired() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(-1);

        assertThat(api.needsRefresh(), is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void doesNotNeedRefreshWhenTokenHasNotExpired() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(Long.MAX_VALUE);

        assertThat(api.needsRefresh(), is(not(true)));
    }

    @Test
    @Category(UnitTest.class)
    public void doesNotNeedRefreshWhenExpiresIsZero() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            TestConfig.getAuthToken(), TestConfig.getRefreshToken());
        api.setExpires(0);

        assertThat(api.needsRefresh(), is(not(true)));
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

        assertThat(originalRefreshToken, not(equalTo(actualRefreshToken)));
        assertThat(originalAuthToken, not(equalTo(actualAccessToken)));

        TestConfig.setAuthToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
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

        assertThat(originalRefreshToken, not(equalTo(actualRefreshToken)));
        assertThat(originalAuthToken, not(equalTo(actualAccessToken)));

        TestConfig.setAuthToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
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

        assertThat(originalRefreshToken, not(equalTo(actualRefreshToken)));
        assertThat(originalAuthToken, not(equalTo(actualAccessToken)));

        TestConfig.setAuthToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
    }
}
