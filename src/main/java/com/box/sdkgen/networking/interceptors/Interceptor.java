package com.box.sdkgen.networking.interceptors;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;

public interface Interceptor {

  FetchOptions beforeRequest(FetchOptions options);

  FetchResponse afterRequest(FetchResponse response);
}
