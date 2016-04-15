package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * A log of events that were retrieved from the events endpoint.
 *
 * <p>An EventLog cannot be instantiated directly. Instead, use one of the static methods to retrieve a log of events.
 * Unlike the {@link EventStream} class, EventLog doesn't support retrieving events in real-time.
 * </p>
 */
public class EventLog implements Iterable<BoxEvent> {
    private static final int ENTERPRISE_LIMIT = 500;
    private static final URLTemplate ENTERPRISE_EVENT_URL_TEMPLATE = new URLTemplate("events?stream_type=admin_logs&"
        + "limit=" + ENTERPRISE_LIMIT);

    private final int chunkSize;
    private final int limit;
    private final String nextStreamPosition;
    private final String streamPosition;
    private final Set<BoxEvent> set;

    private Date startDate;
    private Date endDate;

    EventLog(BoxAPIConnection api, JsonObject json, String streamPosition, int limit) {
        this.streamPosition = streamPosition;
        this.limit = limit;
        this.nextStreamPosition = json.get("next_stream_position").asString();
        this.chunkSize = json.get("chunk_size").asInt();

        this.set = new LinkedHashSet<BoxEvent>(this.chunkSize);
        JsonArray entries = json.get("entries").asArray();
        for (JsonValue entry : entries) {
            this.set.add(new BoxEvent(api, entry.asObject()));
        }
    }

    /**
     * Gets all the enterprise events that occurred within a specified date range.
     * @param  api    the API connection to use.
     * @param  after  the lower bound on the timestamp of the events returned.
     * @param  before the upper bound on the timestamp of the events returned.
     * @param  types  an optional list of event types to filter by.
     * @return        a log of all the events that met the given criteria.
     */
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, Date after, Date before, BoxEvent.Type... types) {
        return getEnterpriseEvents(api, null, after, before, types);
    }

    /**
     * Gets all the enterprise events that occurred within a specified date range, starting from a given position
     * within the event stream.
     * @param  api      the API connection to use.
     * @param  position the starting position of the event stream.
     * @param  after    the lower bound on the timestamp of the events returned.
     * @param  before   the upper bound on the timestamp of the events returned.
     * @param  types    an optional list of event types to filter by.
     * @return          a log of all the events that met the given criteria.
     */
    public static EventLog getEnterpriseEvents(BoxAPIConnection api, String position, Date after, Date before,
        BoxEvent.Type... types) {

        URL url = ENTERPRISE_EVENT_URL_TEMPLATE.build(api.getBaseURL());

        if (position != null || types.length > 0 || after != null
            || before != null) {
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

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        EventLog log = new EventLog(api, responseJSON, position, ENTERPRISE_LIMIT);
        log.setStartDate(after);
        log.setEndDate(before);
        return log;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns an iterator over the events in this log.
     * @return an iterator over the events in this log.
     */
    @Override
    public Iterator<BoxEvent> iterator() {
        return this.set.iterator();
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
        return this.set.size();
    }
}
