package com.box.sdk;

import static com.box.sdk.BoxEvent.EventType.FAILED_LOGIN;
import static com.box.sdk.BoxEvent.EventType.LOGIN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
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
        final int limit = 500;
        final String position = "1152923110369165138";
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
                    assertThat(query, is("stream_type=admin_logs&limit=500"));
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

    @Test
    public void getEnterpriseEventsStream() {
        final int limit = 100;
        final String position = "1152923110369165138";
        BoxEvent.EventType[] eventTypes = {LOGIN, FAILED_LOGIN};
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, containsString("stream_type=admin_logs_streaming"));
                    assertThat(query, not(containsString("created_after")));
                    assertThat(query, not(containsString("created_before")));
                    assertThat(query, containsString("limit=" + limit));
                    assertThat(query, containsString("stream_position=1152923110369165138"));
                    assertThat(query, containsString("event_type=LOGIN,FAILED_LOGIN"));
                    return EMPTY_RESPONSE;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest()
            .limit(limit)
            .position(position)
            .types(eventTypes);
        EventLog.getEnterpriseEventsStream(api, request);
    }

    @Test
    public void getEnterpriseEventsStreamWithoutAnyParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                try {
                    String query = URLDecoder.decode(request.getUrl().getQuery(), "UTF-8");
                    assertThat(query, is("stream_type=admin_logs_streaming&limit=500"));
                    return EMPTY_RESPONSE;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        EventLog.getEnterpriseEventsStream(api, new EnterpriseEventsStreamRequest());
    }

    @Test
    public void createEventLogWhenNextPositionIsNotInteger() {
        BoxAPIConnection api = new BoxAPIConnection("");
        JsonObject json = Json.parse(
            "{\"next_stream_position\": \"1152923112788365709\", \"chunk_size\": 12, \"entries\": []}"
        ).asObject();
        EventLog eventLog = new EventLog(api, json, null, 10);

        assertThat(eventLog.getNextStreamPosition(), is("1152923112788365709"));
    }

    @Test
    public void createEventLogWhenNextPositionIsInteger() {
        BoxAPIConnection api = new BoxAPIConnection("");
        JsonObject json = Json.parse(
            "{\"next_stream_position\": 1152923112788365709, \"chunk_size\": 12, \"entries\": []}"
        ).asObject();
        EventLog eventLog = new EventLog(api, json, null, 10);

        assertThat(eventLog.getNextStreamPosition(), is("1152923112788365709"));
    }
}
