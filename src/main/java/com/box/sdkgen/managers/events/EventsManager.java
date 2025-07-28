package com.box.sdkgen.managers.events;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

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

  public RealtimeServers getEventsWithLongPolling() {
    return getEventsWithLongPolling(new GetEventsWithLongPollingHeaders());
  }

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

  public Events getEvents() {
    return getEvents(new GetEventsQueryParams(), new GetEventsHeaders());
  }

  public Events getEvents(GetEventsQueryParams queryParams) {
    return getEvents(queryParams, new GetEventsHeaders());
  }

  public Events getEvents(GetEventsHeaders headers) {
    return getEvents(new GetEventsQueryParams(), headers);
  }

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
