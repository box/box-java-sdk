package com.box.sdk;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BoxUserTest {
    @Test
    @Category(IntegrationTest.class)
    public void getCurrentUserInfoIsCorrect() throws InterruptedException {
        final String expectedName = "Java SDK";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo();

        assertThat(info.getName(), equalTo(expectedName));
    }
}
