package com.box.sdk;

import static com.box.sdk.EventLog.ENTERPRISE_LIMIT;

import com.box.sdk.BoxEvent.EventType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Class describing request to get Admin Logs Streaming. You can use it's fluent interface to create new request like so:
 * <pre>
 * {@code
 * new EnterpriseEventsStreamRequest().position("stream_position").limit(50);
 * }
 * </pre>
 */
public final class EnterpriseEventsStreamRequest {
    private static final String ADMIN_LOGS_STREAM_TYPE = "admin_logs_streaming";
    private String position;
    private int limit = ENTERPRISE_LIMIT;
    private Collection<String> types = new ArrayList<>();


    /**
     * The starting position of the event stream.
     * @param position the starting position of the event stream.
     * @return request being created.
     */
    public EnterpriseEventsStreamRequest position(String position) {
        this.position = position;
        return this;
    }

    /**
     * The number of entries to be returned in the response.
     * @param limit the number of entries to be returned in the response.
     * @return request being created.
     */
    public EnterpriseEventsStreamRequest limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * List of event types to filter by.
     * @param types list of event types to filter by.
     * @return request being created.
     */
    public EnterpriseEventsStreamRequest types(EventType... types) {
        return typeNames(Arrays.stream(types)
            .map(EventType::toJSONString)
            .toArray(String[]::new)
        );
    }

    /**
     * List of event type names to filter by.
     * @param typeNames list of event type names to filter by.
     * @return request being created.
     */
    public EnterpriseEventsStreamRequest typeNames(String... typeNames) {
        this.types = Arrays.asList(typeNames);
        return this;
    }

    String getPosition() {
        return position;
    }

    int getLimit() {
        return limit;
    }


    Collection<String> getTypes() {
        return types;
    }

    String getStreamType() {
        return ADMIN_LOGS_STREAM_TYPE;
    }
}
