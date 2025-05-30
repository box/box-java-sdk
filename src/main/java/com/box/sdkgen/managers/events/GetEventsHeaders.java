package com.box.sdkgen.managers.events;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetEventsHeaders {

  public Map<String, String> extraHeaders;

  public GetEventsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetEventsHeaders(GetEventsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetEventsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetEventsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetEventsHeaders build() {
      return new GetEventsHeaders(this);
    }
  }
}
