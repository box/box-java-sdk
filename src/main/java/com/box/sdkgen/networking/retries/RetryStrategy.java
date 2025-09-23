package com.box.sdkgen.networking.retries;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;

public interface RetryStrategy {

  boolean shouldRetry(FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber);

  double retryAfter(FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber);
}
