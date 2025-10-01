package com.box.sdkgen.managers.events;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.box.eventstream.EventStream;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.events.Events;
import com.box.sdkgen.schemas.realtimeservers.RealtimeServers;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class EventsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public EventsManager() {
    this.networkSession = new NetworkSession();
  }

  protected EventsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Returns a list of real-time servers that can be used for long-polling updates to the [event
   * stream](#get-events).
   *
   * <p>Long polling is the concept where a HTTP request is kept open until the server sends a
   * response, then repeating the process over and over to receive updated responses.
   *
   * <p>Long polling the event stream can only be used for user events, not for enterprise events.
   *
   * <p>To use long polling, first use this endpoint to retrieve a list of long poll URLs. Next,
   * make a long poll request to any of the provided URLs.
   *
   * <p>When an event occurs in monitored account a response with the value `new_change` will be
   * sent. The response contains no other details as it only serves as a prompt to take further
   * action such as sending a request to the [events endpoint](#get-events) with the last known
   * `stream_position`.
   *
   * <p>After the server sends this response it closes the connection. You must now repeat the long
   * poll process to begin listening for events again.
   *
   * <p>If no events occur for a while and the connection times out you will receive a response with
   * the value `reconnect`. When you receive this response you’ll make another call to this endpoint
   * to restart the process.
   *
   * <p>If you receive no events in `retry_timeout` seconds then you will need to make another
   * request to the real-time server (one of the URLs in the response for this endpoint). This might
   * be necessary due to network errors.
   *
   * <p>Finally, if you receive a `max_retries` error when making a request to the real-time server,
   * you should start over by making a call to this endpoint first.
   */
  public RealtimeServers getEventsWithLongPolling() {
    return getEventsWithLongPolling(new GetEventsWithLongPollingHeaders());
  }

  /**
   * Returns a list of real-time servers that can be used for long-polling updates to the [event
   * stream](#get-events).
   *
   * <p>Long polling is the concept where a HTTP request is kept open until the server sends a
   * response, then repeating the process over and over to receive updated responses.
   *
   * <p>Long polling the event stream can only be used for user events, not for enterprise events.
   *
   * <p>To use long polling, first use this endpoint to retrieve a list of long poll URLs. Next,
   * make a long poll request to any of the provided URLs.
   *
   * <p>When an event occurs in monitored account a response with the value `new_change` will be
   * sent. The response contains no other details as it only serves as a prompt to take further
   * action such as sending a request to the [events endpoint](#get-events) with the last known
   * `stream_position`.
   *
   * <p>After the server sends this response it closes the connection. You must now repeat the long
   * poll process to begin listening for events again.
   *
   * <p>If no events occur for a while and the connection times out you will receive a response with
   * the value `reconnect`. When you receive this response you’ll make another call to this endpoint
   * to restart the process.
   *
   * <p>If you receive no events in `retry_timeout` seconds then you will need to make another
   * request to the real-time server (one of the URLs in the response for this endpoint). This might
   * be necessary due to network errors.
   *
   * <p>Finally, if you receive a `max_retries` error when making a request to the real-time server,
   * you should start over by making a call to this endpoint first.
   *
   * @param headers Headers of getEventsWithLongPolling method
   */
  public RealtimeServers getEventsWithLongPolling(GetEventsWithLongPollingHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/events"),
                        "OPTIONS")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), RealtimeServers.class);
  }

  /**
   * Returns up to a year of past events for a given user or for the entire enterprise.
   *
   * <p>By default this returns events for the authenticated user. To retrieve events for the entire
   * enterprise, set the `stream_type` to `admin_logs_streaming` for live monitoring of new events,
   * or `admin_logs` for querying across historical events. The user making the API call will need
   * to have admin privileges, and the application will need to have the scope `manage enterprise
   * properties` checked.
   */
  public Events getEvents() {
    return getEvents(new GetEventsQueryParams(), new GetEventsHeaders());
  }

  /**
   * Returns up to a year of past events for a given user or for the entire enterprise.
   *
   * <p>By default this returns events for the authenticated user. To retrieve events for the entire
   * enterprise, set the `stream_type` to `admin_logs_streaming` for live monitoring of new events,
   * or `admin_logs` for querying across historical events. The user making the API call will need
   * to have admin privileges, and the application will need to have the scope `manage enterprise
   * properties` checked.
   *
   * @param queryParams Query parameters of getEvents method
   */
  public Events getEvents(GetEventsQueryParams queryParams) {
    return getEvents(queryParams, new GetEventsHeaders());
  }

  /**
   * Returns up to a year of past events for a given user or for the entire enterprise.
   *
   * <p>By default this returns events for the authenticated user. To retrieve events for the entire
   * enterprise, set the `stream_type` to `admin_logs_streaming` for live monitoring of new events,
   * or `admin_logs` for querying across historical events. The user making the API call will need
   * to have admin privileges, and the application will need to have the scope `manage enterprise
   * properties` checked.
   *
   * @param headers Headers of getEvents method
   */
  public Events getEvents(GetEventsHeaders headers) {
    return getEvents(new GetEventsQueryParams(), headers);
  }

  /**
   * Returns up to a year of past events for a given user or for the entire enterprise.
   *
   * <p>By default this returns events for the authenticated user. To retrieve events for the entire
   * enterprise, set the `stream_type` to `admin_logs_streaming` for live monitoring of new events,
   * or `admin_logs` for querying across historical events. The user making the API call will need
   * to have admin privileges, and the application will need to have the scope `manage enterprise
   * properties` checked.
   *
   * @param queryParams Query parameters of getEvents method
   * @param headers Headers of getEvents method
   */
  public Events getEvents(GetEventsQueryParams queryParams, GetEventsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("stream_type", convertToString(queryParams.getStreamType())),
                entryOf("stream_position", convertToString(queryParams.getStreamPosition())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("event_type", convertToString(queryParams.getEventType())),
                entryOf("created_after", convertToString(queryParams.getCreatedAfter())),
                entryOf("created_before", convertToString(queryParams.getCreatedBefore()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/events"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Events.class);
  }

  /** Get an event stream for the Box API */
  public EventStream getEventStream() {
    return getEventStream(new GetEventStreamQueryParams(), new GetEventStreamHeaders());
  }

  /**
   * Get an event stream for the Box API
   *
   * @param queryParams Query parameters of getEvents method
   */
  public EventStream getEventStream(GetEventStreamQueryParams queryParams) {
    return getEventStream(queryParams, new GetEventStreamHeaders());
  }

  /**
   * Get an event stream for the Box API
   *
   * @param headers Headers of getEvents method
   */
  public EventStream getEventStream(GetEventStreamHeaders headers) {
    return getEventStream(new GetEventStreamQueryParams(), headers);
  }

  /**
   * Get an event stream for the Box API
   *
   * @param queryParams Query parameters of getEvents method
   * @param headers Headers of getEvents method
   */
  public EventStream getEventStream(
      GetEventStreamQueryParams queryParams, GetEventStreamHeaders headers) {
    return new EventStream.Builder(this, queryParams).headersInput(headers).build();
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public EventsManager build() {
      return new EventsManager(this);
    }
  }
}
