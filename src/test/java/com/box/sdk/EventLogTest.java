package com.box.sdk;

import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EventLogTest {

    @Test
    @Category(IntegrationTest.class)
    public void getEnterpriseEventsReturnsAtLeastOneEvent() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        EventLog events = EventLog.getEnterpriseEvents(api, after, before);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

    @Test
    @Category(IntegrationTest.class)
    public void getEnterpriseEventsGmtPlus530() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        EventLog events = EventLog.getEnterpriseEvents(api, after, before);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

}
