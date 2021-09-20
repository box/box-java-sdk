package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

public class EventLogIT {

    @Test
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

    @Test
    public void getEnterpriseEventsGmtPlus530WithLimit() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        int limit = 5;
        EventLog events = EventLog.getEnterpriseEvents(api, "", after, before, limit);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
        assertThat(events.getLimit(), is(equalTo(limit)));
    }
}
