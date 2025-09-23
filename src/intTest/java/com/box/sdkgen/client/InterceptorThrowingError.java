package com.box.sdkgen.client;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;

public class InterceptorThrowingError implements Interceptor {

  @Override
  public FetchOptions beforeRequest(FetchOptions options) {
    return options;
  }

  @Override
  public FetchResponse afterRequest(FetchResponse response) {
    return new FetchResponse.Builder(400, response.getHeaders())
        .url(response.getUrl())
        .data(response.getData())
        .content(response.getContent())
        .build();
  }
}
