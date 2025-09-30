# Configuration

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Configuration](#configuration)
  - [Max retry attempts](#max-retry-attempts)
  - [Custom retry strategy](#custom-retry-strategy)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Max retry attempts

The default maximum number of retries in case of failed API call is 5.
To change this number you should initialize `BoxRetryStrategy` with the new value and pass it to `NetworkSession`.

```java
BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("DEVELOPER_TOKEN");
NetworkSession session = new NetworkSession.Builder()
    .retryStrategy(new BoxRetryStrategy.Builder().maxAttempts(3).build())
    .build();
BoxClient client = new BoxClient.Builder(auth)
    .networkSession(session)
    .build();
```

## Custom retry strategy

You can also implement your own retry strategy by subclassing `RetryStrategy` and overriding `shouldRetry` and `retryAfter` methods.
This example shows how to set custom strategy that retries on 5xx status codes and waits 1 second between retries.

```java
BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("DEVELOPER_TOKEN");
RetryStrategy customRetryStrategy = new RetryStrategy() {
    @Override
    public boolean shouldRetry(FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
        return fetchResponse.status >= 500;
    }

    @Override
    public double retryAfter(FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
        return 1.0;
    }
};
NetworkSession session = new NetworkSession.Builder()
    .retryStrategy(customRetryStrategy)
    .build();
BoxClient client = new BoxClient.Builder(auth)
    .networkSession(session)
    .build();
```
