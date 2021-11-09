package com.box.sdk;

import static com.box.sdk.BoxEvent.EventType.FAILED_LOGIN;
import static com.box.sdk.BoxEvent.EventType.LOGIN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import java.net.URLDecoder;
import java.util.Date;
import org.junit.Test;

public class EventLogTest {

    private static final BoxJSONResponse EMPTY_RESPONSE = new BoxJSONResponse() {
        @Override
        public String getJSON() {
            return "{\"entries\": [], \"next_stream_position\": \"new_position\", \"chunk_size\": 0}";
        }
    };

    @Test
    public void getEnterpriseEvents() {
        final Date after = new Date(0L);
        final Date before = new Date(System.currentTimeMillis());
        final int limit = 100;
        final String position = "some_position";
        BoxEvent.EventType[] eventTypes = {LOGIN, FAILED_LOGIN};
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, containsString("stream_type=admin_logs"));
                    assertThat(query, containsString("created_after=" + BoxDateFormat.format(after)));
                    assertThat(query, containsString("created_before=" + BoxDateFormat.format(before)));
                    assertThat(query, containsString("limit=" + limit));
                    assertThat(query, containsString("stream_position=" + position));
                    assertThat(query, containsString("event_type=LOGIN,FAILED_LOGIN"));
                    return EMPTY_RESPONSE;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EnterpriseEventsRequest request = new EnterpriseEventsRequest()
            .after(after)
            .before(before)
            .limit(limit)
            .position(position)
            .types(eventTypes);
        EventLog.getEnterpriseEvents(api, request);
    }

    @Test
    public void getEnterpriseEventsWithoutAnyParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, is("stream_type=admin_logs"));
                    return EMPTY_RESPONSE;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EventLog.getEnterpriseEvents(api, new EnterpriseEventsRequest());
    }

    @Test
    public void getEnterpriseEventsAddsLimitOnlyIfDifferentThanDefault() {
        final int limit = 100;
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, is("stream_type=admin_logs&limit=" + limit));
                    return EMPTY_RESPONSE;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EventLog.getEnterpriseEvents(api, new EnterpriseEventsRequest().limit(limit));
    }
}
