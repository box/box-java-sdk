package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
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
        BoxAPIConnection api = jwtApiForServiceAccount();
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());

        EnterpriseEventsRequest request = new EnterpriseEventsRequest().after(after).before(before);
        EventLog events = EventLog.getEnterpriseEvents(api, request);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

    @Test
    public void getEnterpriseEventsGmtPlus530() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());

        EnterpriseEventsRequest request = new EnterpriseEventsRequest().after(after).before(before);
        EventLog events = EventLog.getEnterpriseEvents(api, request);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

    @Test
    public void getEnterpriseEventsGmtPlus530WithLimit() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        int limit = 5;

        EnterpriseEventsRequest request = new EnterpriseEventsRequest().after(after).before(before).limit(limit);
        EventLog events = EventLog.getEnterpriseEvents(api, request);

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
        assertThat(events.getLimit(), is(equalTo(limit)));
    }

    @Test
    public void getEnterpriseEventsStreamReturnsAtLeastOneEvent() {
        BoxAPIConnection api = jwtApiForServiceAccount();

        EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest();
        EventLog events = EventLog.getEnterpriseEventsStream(api, request);

        assertThat(events.getSize(), is(not(0)));
    }

    @Test
    public void getEnterpriseEventsReturnsAtLeastOneEventDeprecated() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        EventLog events = EventLog.getEnterpriseEvents(api, new EnterpriseEventsRequest().after(after).before(before));

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

    @Test
    public void getEnterpriseEventsGmtPlus530Deprecated() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        EventLog events = EventLog.getEnterpriseEvents(api, new EnterpriseEventsRequest().after(after).before(before));

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
    }

    @Test
    public void getEnterpriseEventsGmtPlus530WithLimitDeprecated() {
        BoxAPIConnection api = jwtApiForServiceAccount();
        System.setProperty("user.timezone", "Asia/Calcutta");
        TimeZone.setDefault(null);
        Date after = new Date(0L);
        Date before = new Date(System.currentTimeMillis());
        int limit = 5;
        EventLog events = EventLog.getEnterpriseEvents(
            api, new EnterpriseEventsRequest().after(after).before(before).limit(limit)
        );

        assertThat(events.getSize(), is(not(0)));
        assertThat(events.getStartDate(), is(equalTo(after)));
        assertThat(events.getEndDate(), is(equalTo(before)));
        assertThat(events.getLimit(), is(equalTo(limit)));
    }
}
