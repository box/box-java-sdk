package com.box.sdkgen.test.client;

import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;

public class InterceptorChangingResponse implements Interceptor {

  @Override
  public FetchOptions beforeRequest(FetchOptions options) {
    return options;
  }

  @Override
  public FetchResponse afterRequest(FetchResponse response) {
    return new FetchResponse.Builder(response.getStatus(), response.getHeaders())
        .url(response.getUrl())
        .data(jsonToSerializedData("{\"id\": \"123\", \"type\": \"user\"}"))
        .content(response.getContent())
        .build();
  }
}
