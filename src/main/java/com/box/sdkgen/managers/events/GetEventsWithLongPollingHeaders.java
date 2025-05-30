package com.box.sdkgen.managers.events;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetEventsWithLongPollingHeaders {

  public Map<String, String> extraHeaders;

  public GetEventsWithLongPollingHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetEventsWithLongPollingHeaders(GetEventsWithLongPollingHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetEventsWithLongPollingHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetEventsWithLongPollingHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetEventsWithLongPollingHeaders build() {
      return new GetEventsWithLongPollingHeaders(this);
    }
  }
}
