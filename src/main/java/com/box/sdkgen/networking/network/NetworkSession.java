package com.box.sdkgen.networking.network;

import com.box.sdkgen.internal.logging.DataSanitizer;
import com.box.sdkgen.networking.baseurls.BaseUrls;
import com.box.sdkgen.networking.boxnetworkclient.BoxNetworkClient;
import com.box.sdkgen.networking.interceptors.Interceptor;
import com.box.sdkgen.networking.networkclient.NetworkClient;
import com.box.sdkgen.networking.retries.BoxRetryStrategy;
import com.box.sdkgen.networking.retries.RetryStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkSession {
  protected Map<String, String> additionalHeaders = new HashMap<>();

  protected BaseUrls baseUrls = new BaseUrls();

  protected List<Interceptor> interceptors = new ArrayList<>();

  protected NetworkClient networkClient;

  protected RetryStrategy retryStrategy;

  protected DataSanitizer dataSanitizer;

  public NetworkSession() {
    networkClient = new BoxNetworkClient();
    retryStrategy = new BoxRetryStrategy();
    dataSanitizer = new DataSanitizer();
  }

  protected NetworkSession(Builder builder) {
    this.additionalHeaders = builder.additionalHeaders;
    this.baseUrls = builder.baseUrls;
    this.networkClient = builder.networkClient;
    this.interceptors = builder.interceptors;
    this.retryStrategy = builder.retryStrategy;
    this.dataSanitizer = builder.dataSanitizer;
  }

  public NetworkSession withAdditionalHeaders() {
    return withAdditionalHeaders(new HashMap<>());
  }

  public NetworkSession withAdditionalHeaders(Map<String, String> additionalHeaders) {
    Map<String, String> newHeaders = new HashMap<>();
    newHeaders.putAll(this.additionalHeaders);
    newHeaders.putAll(additionalHeaders);
    return new NetworkSession.Builder()
        .additionalHeaders(newHeaders)
        .baseUrls(this.baseUrls)
        .interceptors(this.interceptors)
        .networkClient(this.networkClient)
        .retryStrategy(this.retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public NetworkSession withCustomBaseUrls(BaseUrls baseUrls) {
    return new Builder()
        .additionalHeaders(this.additionalHeaders)
        .baseUrls(baseUrls)
        .interceptors(this.interceptors)
        .networkClient(this.networkClient)
        .retryStrategy(this.retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public NetworkSession withInterceptors(List<Interceptor> interceptors) {
    List<Interceptor> newInterceptors =
        Stream.concat(this.interceptors.stream(), interceptors.stream())
            .collect(Collectors.toList());
    return new Builder()
        .additionalHeaders(this.additionalHeaders)
        .baseUrls(this.baseUrls)
        .interceptors(newInterceptors)
        .networkClient(this.networkClient)
        .retryStrategy(this.retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public NetworkSession withNetworkClient(NetworkClient networkClient) {
    return new Builder()
        .additionalHeaders(this.additionalHeaders)
        .baseUrls(this.baseUrls)
        .interceptors(this.interceptors)
        .networkClient(networkClient)
        .retryStrategy(this.retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public NetworkSession withRetryStrategy(RetryStrategy retryStrategy) {
    return new Builder()
        .additionalHeaders(this.additionalHeaders)
        .baseUrls(this.baseUrls)
        .interceptors(this.interceptors)
        .networkClient(this.networkClient)
        .retryStrategy(retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public NetworkSession withDataSanitizer(DataSanitizer dataSanitizer) {
    return new Builder()
        .additionalHeaders(this.additionalHeaders)
        .baseUrls(this.baseUrls)
        .interceptors(this.interceptors)
        .networkClient(this.networkClient)
        .retryStrategy(this.retryStrategy)
        .dataSanitizer(dataSanitizer)
        .build();
  }

  public Map<String, String> getAdditionalHeaders() {
    return additionalHeaders;
  }

  public BaseUrls getBaseUrls() {
    return baseUrls;
  }

  public NetworkClient getNetworkClient() {
    return networkClient;
  }

  public List<Interceptor> getInterceptors() {
    return interceptors;
  }

  public RetryStrategy getRetryStrategy() {
    return retryStrategy;
  }

  public DataSanitizer getDataSanitizer() {
    return dataSanitizer;
  }

  public static class Builder {

    protected Map<String, String> additionalHeaders = new HashMap<>();

    protected BaseUrls baseUrls = new BaseUrls();

    protected NetworkClient networkClient;

    protected List<Interceptor> interceptors = new ArrayList<>();

    protected RetryStrategy retryStrategy;

    protected DataSanitizer dataSanitizer;

    public Builder() {
      networkClient = new BoxNetworkClient();
      retryStrategy = new BoxRetryStrategy();
      dataSanitizer = new DataSanitizer();
    }

    public Builder additionalHeaders(Map<String, String> additionalHeaders) {
      this.additionalHeaders = additionalHeaders;
      return this;
    }

    public Builder baseUrls(BaseUrls baseUrls) {
      this.baseUrls = baseUrls;
      return this;
    }

    public Builder networkClient(NetworkClient networkClient) {
      this.networkClient = networkClient;
      return this;
    }

    public Builder interceptors(List<Interceptor> interceptors) {
      this.interceptors = interceptors;
      return this;
    }

    public Builder retryStrategy(RetryStrategy retryStrategy) {
      this.retryStrategy = retryStrategy;
      return this;
    }

    public Builder dataSanitizer(DataSanitizer dataSanitizer) {
      this.dataSanitizer = dataSanitizer;
      return this;
    }

    public NetworkSession build() {
      return new NetworkSession(this);
    }
  }
}
