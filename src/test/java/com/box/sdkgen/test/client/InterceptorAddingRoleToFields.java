package com.box.sdkgen.test.client;

import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;

public class InterceptorAddingRoleToFields implements Interceptor {

  @Override
  public FetchOptions beforeRequest(FetchOptions options) {
    return new FetchOptions.Builder(options.getUrl(), options.getMethod())
        .params(mergeMaps(options.getParams(), mapOf(entryOf("fields", "role"))))
        .headers(options.getHeaders())
        .data(options.getData())
        .fileStream(options.getFileStream())
        .multipartData(options.getMultipartData())
        .contentType(options.getContentType())
        .responseFormat(options.getResponseFormat())
        .auth(options.getAuth())
        .networkSession(options.getNetworkSession())
        .build();
  }

  @Override
  public FetchResponse afterRequest(FetchResponse response) {
    return response;
  }
}
