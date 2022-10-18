package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A log of events that were retrieved from the events endpoint.
 *
 * <p>An EventLog cannot be instantiated directly. Instead, use one of the static methods to retrieve a log of events.
 * Unlike the {@link EventStream} class, EventLog doesn't support retrieving events in real-time.
 * </p>
 */
public class EventLog implements Iterable<BoxEvent> {

    static final int ENTERPRISE_LIMIT = 500;
    /**
     * Enterprise Event URL Template.
     */
    public static final URLTemplate ENTERPRISE_EVENT_URL_TEMPLATE = new URLTemplate("events?stream_type=admin_logs&"
        + "limit=" + ENTERPRISE_LIMIT);
    private final int chunkSize;
    private final int limit;
    private final String nextStreamPosition;
    private final String streamPosition;
    private final Set<BoxEvent> events;

    private Date startDate;
    private Date endDate;

    EventLog(BoxAPIConnection api, JsonObject json, String streamPosition, int limit) {
        this.streamPosition = streamPosition;
        this.limit = limit;
        JsonValue nextStreamPosition = json.get("next_stream_position");
        if (nextStreamPosition.isString()) {
            this.nextStreamPosition = nextStreamPosition.asString();
        } else {
            this.nextStreamPosition = nextStreamPosition.toString();
        }
        this.chunkSize = json.get("chunk_size").asInt();

        this.events = new LinkedHashSet<>(this.chunkSize);
        JsonArray entries = json.get("entries").asArray();
        for (JsonValue entry : entries) {
            this.events.add(new BoxEvent(api, entry.asObject()));
        }
    }

    /**
     * Gets all the enterprise events that occurred within a specified date range, starting from a given position
     * within the event stream.
     *
     * @param api      the API connection to use.
     * @param position the starting position of the event stream.
     * @param after    the lower bound on the timestamp of the events returned.
     * @param before   the upper bound on the timestamp of the events returned.
     * @param types    an optional list of event types to filter by.
     * @return a log of all the events that met the given criteria.
     * @deprecated Use {@link #getEnterpriseEvents(BoxAPIConnection, EnterpriseEventsRequest)}
     */
    @Deprecated
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, String position, Date after, Date before,
                                               BoxEvent.Type... types) {
        return getEnterpriseEvents(api, position, after, before, ENTERPRISE_LIMIT, types);
    }

    /**
     * Gets all the enterprise events that occurred within a specified date range.
     *
     * @param api    the API connection to use.
     * @param after  the lower bound on the timestamp of the events returned.
     * @param before the upper bound on the timestamp of the events returned.
     * @param types  an optional list of event types to filter by.
     * @return a log of all the events that met the given criteria.
     * @deprecated Use {@link #getEnterpriseEvents(BoxAPIConnection, EnterpriseEventsRequest)}
     */
    @Deprecated
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, Date after, Date before, BoxEvent.Type... types) {
        return getEnterpriseEvents(api, null, after, before, ENTERPRISE_LIMIT, types);
    }

    /**
     * Gets all the enterprise events that occurred within a specified date range, starting from a given position
     * within the event stream.
     *
     * @param api      the API connection to use.
     * @param position the starting position of the event stream.
     * @param after    the lower bound on the timestamp of the events returned.
     * @param before   the upper bound on the timestamp of the events returned.
     * @param limit    the number of entries to be returned in the response.
     * @param types    an optional list of event types to filter by.
     * @return a log of all the events that met the given criteria.
     * @deprecated Use {@link #getEnterpriseEvents(BoxAPIConnection, EnterpriseEventsRequest)}
     */
    @Deprecated
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, String position, Date after, Date before,
                                               int limit, BoxEvent.Type... types) {

        URL url = ENTERPRISE_EVENT_URL_TEMPLATE.build(api.getBaseURL());

        if (position != null || types.length > 0 || after != null
            || before != null || limit != ENTERPRISE_LIMIT) {
            QueryStringBuilder queryBuilder = new QueryStringBuilder(url.getQuery());

            if (after != null) {
                queryBuilder.appendParam("created_after",
                    BoxDateFormat.format(after));
            }

            if (before != null) {
                queryBuilder.appendParam("created_before",
                    BoxDateFormat.format(before));
            }

            if (position != null) {
                queryBuilder.appendParam("stream_position", position);
            }

            if (limit != ENTERPRISE_LIMIT) {
                queryBuilder.appendParam("limit", limit);
            }

            if (types.length > 0) {
                StringBuilder filterBuilder = new StringBuilder();
                for (BoxEvent.Type filterType : types) {
                    filterBuilder.append(filterType.name());
                    filterBuilder.append(',');
                }
                filterBuilder.deleteCharAt(filterBuilder.length() - 1);
                queryBuilder.appendParam("event_type", filterBuilder.toString());
            }

            try {
                url = queryBuilder.addToURL(url);
            } catch (MalformedURLException e) {
                throw new BoxAPIException("Couldn't append a query string to the provided URL.");
            }
        }

        BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            EventLog log = new EventLog(api, responseJSON, position, limit);
            log.setStartDate(after);
            log.setEndDate(before);
            return log;
        }
    }

    /**
     * Method reads from the `admin-logs` stream and returns {@link BoxEvent} {@link Iterator}.
     * The emphasis for this stream is on completeness over latency,
     * which means that Box will deliver admin events in chronological order and without duplicates,
     * but with higher latency. You can specify start and end time/dates. This method
     * will only work with an API connection for an enterprise admin account
     * or service account with manage enterprise properties.
     * You can specify a date range to limit when events occured, starting from a given position within the
     * event stream, set limit or specify event types that should be filtered.
     * Example:
     * <pre>
     * {@code
     * EnterpriseEventsRequest request = new EnterpriseEventsRequest()
     *     .after(after)        // The lower bound on the timestamp of the events returned.
     *     .before(before)      // The upper bound on the timestamp of the events returned.
     *     .limit(20)           // The number of entries to be returned in the response.
     *     .position(position)  // The starting position of the event stream.
     *     .types(EventType.LOGIN, EventType.FAILED_LOGIN); // List of event types to filter by.
     * EventLog.getEnterpriseEvents(api, request);
     * }
     * </pre>
     *
     * @param api                     the API connection to use.
     * @param enterpriseEventsRequest request to get events.
     * @return a log of all the events that met the given criteria.
     */
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, EnterpriseEventsRequest enterpriseEventsRequest) {
        EventLogRequest request = new EventLogRequest(
            enterpriseEventsRequest.getBefore(),
            enterpriseEventsRequest.getAfter(),
            enterpriseEventsRequest.getPosition(),
            enterpriseEventsRequest.getLimit(),
            enterpriseEventsRequest.getTypes()
        );
        return getEnterpriseEventsForStreamType(api, enterpriseEventsRequest.getStreamType(), request);
    }

    /**
     * Method reads from the `admin-logs-streaming` stream and returns {@link BoxEvent} {@link Iterator}
     * The emphasis for this feed is on low latency rather than chronological accuracy, which means that Box may return
     * events more than once and out of chronological order. Events are returned via the API around 12 seconds after they
     * are processed by Box (the 12 seconds buffer ensures that new events are not written after your cursor position).
     * Only two weeks of events are available via this feed, and you cannot set start and end time/dates. This method
     * will only work with an API connection for an enterprise admin account
     * or service account with manage enterprise properties.
     * You can specify a starting from a given position within the event stream,
     * set limit or specify event types that should be filtered.
     * Example:
     * <pre>
     * {@code
     * EnterpriseEventsStreamRequest request = new EnterpriseEventsStreamRequest()
     *     .limit(200)          // The number of entries to be returned in the response.
     *     .position(position)  // The starting position of the event stream.
     *     .types(EventType.LOGIN, EventType.FAILED_LOGIN); // List of event types to filter by.
     * EventLog.getEnterpriseEventsStream(api, request);
     * }
     * </pre>
     *
     * @param api                           the API connection to use.
     * @param enterpriseEventsStreamRequest request to get events.
     * @return a log of all the events that met the given criteria.
     */
    public static EventLog getEnterpriseEventsStream(
        BoxAPIConnection api, EnterpriseEventsStreamRequest enterpriseEventsStreamRequest
    ) {
        EventLogRequest request = new EventLogRequest(
            null,
            null,
            enterpriseEventsStreamRequest.getPosition(),
            enterpriseEventsStreamRequest.getLimit(),
            enterpriseEventsStreamRequest.getTypes()
        );
        return getEnterpriseEventsForStreamType(api, enterpriseEventsStreamRequest.getStreamType(), request);
    }

    private static EventLog getEnterpriseEventsForStreamType(
        BoxAPIConnection api, String streamType, EventLogRequest request
    ) {
        URL url = new URLTemplate("events?").build(api.getBaseURL());
        QueryStringBuilder queryBuilder = new QueryStringBuilder(url.getQuery());
        queryBuilder.appendParam("stream_type", streamType);
        addParamsToQuery(request, queryBuilder);

        try {
            url = queryBuilder.addToURL(url);
        } catch (MalformedURLException e) {
            throw new BoxAPIException("Couldn't append a query string to the provided URL.");
        }

        BoxJSONRequest apiRequest = new BoxJSONRequest(api, url, "GET");
        try (BoxJSONResponse response = apiRequest.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            EventLog log = new EventLog(api, responseJSON, request.getPosition(), request.getLimit());
            log.setStartDate(request.getAfter());
            log.setEndDate(request.getBefore());
            return log;
        }
    }

    private static void addParamsToQuery(EventLogRequest request, QueryStringBuilder queryBuilder) {
        queryBuilder.appendParam("limit", request.getLimit());

        if (request.getAfter() != null) {
            queryBuilder.appendParam("created_after", BoxDateFormat.format(request.getAfter()));
        }
        if (request.getBefore() != null) {
            queryBuilder.appendParam("created_before", BoxDateFormat.format(request.getBefore()));
        }
        if (request.getPosition() != null) {
            queryBuilder.appendParam("stream_position", request.getPosition());
        }
        if (request.getTypes().size() > 0) {
            String types = String.join(",", request.getTypes());
            queryBuilder.appendParam("event_type", types);
        }
    }

    /**
     * Returns an iterator over the events in this log.
     *
     * @return an iterator over the events in this log.
     */
    @Override
    public Iterator<BoxEvent> iterator() {
        return this.events.iterator();
    }

    /**
     * Gets the date of the earliest event in this log.
     *
     * <p>The value returned by this method corresponds to the <code>created_after</code> URL parameter that was used
     * when retrieving the events in this EventLog.</p>
     *
     * @return the date of the earliest event in this log.
     */
    public Date getStartDate() {
        return this.startDate;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the date of the latest event in this log.
     *
     * <p>The value returned by this method corresponds to the <code>created_before</code> URL parameter that was used
     * when retrieving the events in this EventLog.</p>
     *
     * @return the date of the latest event in this log.
     */
    public Date getEndDate() {
        return this.endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the maximum number of events that this event log could contain given its start date, end date, and stream
     * position.
     *
     * <p>The value returned by this method corresponds to the <code>limit</code> URL parameter that was used when
     * retrieving the events in this EventLog.</p>
     *
     * @return the maximum number of events.
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Gets the starting position of the events in this log within the event stream.
     *
     * <p>The value returned by this method corresponds to the <code>stream_position</code> URL parameter that was used
     * when retrieving the events in this EventLog.</p>
     *
     * @return the starting position within the event stream.
     */
    public String getStreamPosition() {
        return this.streamPosition;
    }

    /**
     * Gets the next position within the event stream for retrieving subsequent events.
     *
     * <p>The value returned by this method corresponds to the <code>next_stream_position</code> field returned by the
     * API's events endpoint.</p>
     *
     * @return the next position within the event stream.
     */
    public String getNextStreamPosition() {
        return this.nextStreamPosition;
    }

    /**
     * Gets the number of events in this log, including duplicate events.
     *
     * <p>The chunk size may not be representative of the number of events returned by this EventLog's iterator because
     * the iterator will automatically ignore duplicate events.</p>
     *
     * <p>The value returned by this method corresponds to the <code>chunk_size</code> field returned by the API's
     * events endpoint.</p>
     *
     * @return the number of events, including duplicates.
     */
    public int getChunkSize() {
        return this.chunkSize;
    }

    /**
     * Gets the number of events in this list, excluding duplicate events.
     *
     * <p>The size is guaranteed to be representative of the number of events returned by this EventLog's iterator.</p>
     *
     * @return the number of events, excluding duplicates.
     */
    public int getSize() {
        return this.events.size();
    }

    private static final class EventLogRequest {
        private final Date before;
        private final Date after;
        private final String position;
        private final Integer limit;
        private final Collection<String> types;

        private EventLogRequest(
            Date before,
            Date after,
            String position,
            Integer limit,
            Collection<String> types
        ) {
            this.before = before;
            this.after = after;
            this.position = position;
            this.limit = limit;
            this.types = types;
        }

        private Date getBefore() {
            return before;
        }

        private Date getAfter() {
            return after;
        }

        private String getPosition() {
            return position;
        }

        private Integer getLimit() {
            return limit;
        }

        private Collection<String> getTypes() {
            return types;
        }
    }
}
