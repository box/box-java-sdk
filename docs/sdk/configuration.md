# Configuration

- [Proxy configuration](#proxy-configuration)
    - [Custom proxy authenticator](#custom-proxy-authenticator)
        - [Example: NTLM authenticator](#example-ntlm-authenticator) 
- [Configure retries of calls and timeouts](#configure-retries-of-calls-and-timeouts)
    - [Maximum retries](#maximum-retries)
    - [Connection timeout](#connection-timeout)
    - [Read timeout](#read-timeout)
- [URLs configuration](#urls-configuration)
    - [Base URL](#base-url)
    - [Base Upload URL](#base-upload-url)
    - [Base App URL](#base-app-url)
    - [Token URL](#token-url-deprecated)
    - [Revoke URL](#revoke-url-deprecated)
- [SSL configuration](#ssl-configuration)
- [Instrumenation of OpenTelemetry](#instrumenation-of-opentelemetry)
- [OkHttp Default Retry Behavior](#okhttp-default-retry-behavior)

# Proxy configuration

To set up proxy
use [BoxApiConnection.setProxy](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxy-java.net.Proxy-)
to set proxy address
and [BoxApiConnection.setProxyBasicAuthentication][set-basic-proxy-auth]
to set username and password required by proxy:

```java
BoxAPIConnection api=new BoxAPIConnection("access_token");
Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy_url",8888));
// You can use any subclass of BoxAPIConnection
api.setProxy(proxy);
api.setProxyBasicAuthentication("proxyUsername", "proxyPassword");
```
Proxy username and password will be used only if provided `SocketAddress` is an instance of 
`InetSocketAddress`. If you would like to use a custom  `SocketAddress` you can provide your own
`okhttp3.Authenticator` using [BoxApiConnection.setProxyAuthenticator(Authenticator)][set-proxy-authenticator]


## Custom proxy authenticator
By using [BoxApiConnection.setProxyBasicAuthentication][set-basic-proxy-auth] you can enable default 
proxy authenticator that handles only Basic authentication. But you can provide your own authenticator by using
[BoxApiConnection.setProxyAuthenticator(Authenticator)][set-proxy-authenticator].

To do that you will need to add a dependency to your project:
```
"com.squareup.okhttp3:okhttp:XXX"
```
Please match the version with what SDK is using by checking `build.gradle` 
and looking for entry `implementation "com.squareup.okhttp3:okhttp:"`.

Now you can add an authenticator. by calling

```java
BoxAPIConnection api = new BoxAPIConnection("access_token");
Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy_url",8888));
api.setProxy(proxy);
api.setProxyAuthenticator((route, response) -> response
  .request()
  .newBuilder()
  .addHeader("Proxy-Authorization", "My custom authenticator")
  .build()
);
```

### Example: NTLM authenticator

For example, you can add NTLM authorization. This is example NTLM authenticator that
is using parts from Apache Http Client 5.

```java
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.apache.hc.client5.http.impl.auth.NTLMEngineException;

public class NTLMAuthenticator implements Authenticator {
    final NTLMEngineImpl engine = new NTLMEngineImpl();
    private final String domain;
    private final String username;
    private final String password;
    private final String ntlmMsg1;

    public NTLMAuthenticator(String username, String password, String domain) {
        this.domain = domain;
        this.username = username;
        this.password = password;
        String localNtlmMsg1 = null;
        try {
            localNtlmMsg1 = engine.generateType1Msg(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ntlmMsg1 = localNtlmMsg1;
    }

    @Override
    public Request authenticate(Route route, Response response) {
        if(response.code() == 407 && "Proxy authorization required".equals(response.message())) {
            String ntlmChallenge = response.headers("Proxy-Authenticate")
                    .stream()
                    .filter(h -> h.startsWith("NTLM "))
                    .findFirst().orElse("");
            if(ntlmChallenge.length() > 5) {
                try {
                    String ntlmMsg3 = engine.generateType3Msg(username, password.toCharArray(), domain, "ok-http-example-ntlm", ntlmChallenge.substring(5));
                    return response.request().newBuilder().header("proxy-Authorization", "NTLM " + ntlmMsg3).build();
                } catch (NTLMEngineException e) {
                    throw new RuntimeException(e);
                }
            }
            return response.request().newBuilder().header("proxy-Authorization", "NTLM " + ntlmMsg1).build();
        }
        return response.request();
    }
}
```

The `NTLMEngineImpl` could be created by using Apache implementation that can be found 
[here](https://github.com/apache/httpcomponents-client/blob/master/httpclient5/src/main/java/org/apache/hc/client5/http/impl/auth/NTLMEngineImpl.java).
You can add a dependency to `org.apache.httpcomponents.client5:httpclient5:5.1.3`. 
Copy the `NTLMEngineImpl` class and add it to your source.

Now you can use custom NTML Authenticator in your `BoxAPIConnection`:
```java
BoxAPIConnection api = new BoxAPIConnection("access_token");
Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy_url",8888));
api.setProxy(proxy);
api.setProxyAuthenticator(new NTLMAuthenticator("some proxy username", "some proxy password", "proxy workgroup"));
```

[set-basic-proxy-auth]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxyBasicAuthentication-java.lang.String-java.lang.String-
[set-proxy-authenticator]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxyAuthenticator-okhttp3.Authenticator-

# Configure retries of calls and timeouts
SDK can retry failed calls when:
 - failed writting request body
 - when recieved HTTP response code:
   - 429 - rate limit exceeded
   - 5XX - internal server error
   - 400 error with error that `exp` claim has expired. This usially means there is a clock skew.

SDK is using exponnetial strategy to calculate time between retries. 
 If response contains `Retry-After` header its value will be used as a wait time between calls.
You can check details in `com.box.sdk.BoxAPIRequest.send(com.box.sdk.ProgressListener)` method.

## Maximum retries

To configure how many times API will retry calls
use [BoxApiConnection.setMaxRetryAttempts](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setMaxRetryAttempts-int-):

```java
// You can use any subclass of BoxAPIConnection
api.setMaxRetryAttempts(10);
```

default value for retry attempts is `5`.

## Connection timeout

To set up how log (in milliseconds) API waits to establish connection
use [BoxApiConnection.setConnectTimeout](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setConnectTimeout-int-):

```java
// You can use any subclass of BoxAPIConnection
int connectionTimeout = 100; // timeout in milliseconds
api.setConnectTimeout(connectionTimeout);
```

default value is `0` which mean API waits forever to establish connection.

## Read timeout

To set up how log (in milliseconds) API waits to read data from connection
use [BoxApiConnection.setReadTimeout](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setReadTimeout-int-):

```java
// You can use any subclass of BoxAPIConnection
int readTimeout = 100; // timeout in milliseconds
api.setReadTimeout(readTimeout);
```

default value is `0` which mean API waits forever to read data from connection.

# URLs configuration

### Base URL
The default base URL used for making API calls to Box can be changed by calling `BoxAPIConnection#setBaseURL(String)` 
method on `BoxApiConnection`. Default value is `https://api.box.com/`.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseURL("https://example.com");
String baseUrl = api.getBaseURL();    // will produce "https://example.com/2.0/"
```

Setting Base URL changes the Token and Revoke URL as well:
```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseURL("https://example.com");

String baseUrl = api.getBaseURL();    // will produce "https://example.com/2.0/"
String tokenUrl = api.getRevokeURL(); // will produce "https://example.com/oauth2/revoke"
String revokeUrl = api.getTokenURL(); // will produce "https://example.com/oauth2/token"
```

### Base Upload URL
The default URL used for uploads can be changed by calling `BoxAPIConnection#setBaseUploadURL(String)` method on `BoxApiConnection`.
Default value is `https://upload.box.com/api/`.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseUploadURL("https://upload.example.com");
api.getBaseUploadURL(); // will produce "https://upload.example.com/2.0/"
```

### Base App URL
The default base app URL can be changed by calling `BoxAPIConnection#setBaseAppUrl()` method on `BoxApiConnection`. 
Default value is https://app.box.com.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseAppUrl("https://example.app.com");
api.getBaseAppUrl(); // will produce "https://app.example.com"
```

### Token URL (deprecated)
The default URL used for getting token can be changed by calling `setTokenURL()` method on `BoxApiConnection`. 
Default value is https://api.box.com/oauth2/token. This method is deprecated. Use `BoxAPIConnection#setBaseURL(String)` 
instead.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setTokenURL("https://example.com/token");
```

If you use `setTokenUrl` this URL will be used over the one coming from `setBaseUrl` when doing authentication. 

### Revoke URL (deprecated)
The default URL used for invalidating token can be changed by calling `setRevokeURL()` method on `BoxApiConnection`. 
Default value is https://api.box.com/oauth2/revoke. This method is deprecated. Use `BoxAPIConnection#setBaseURL(String)`
instead.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setRevokeURL("https://example.com/revoke");
```

If you use `setRevokeUrl` this URL will be used over the one coming from`setBaseUrl` when doing authentication.

# SSL configuration
You can override default settings used to verify SSL certificates. 
This can be used to allow using self-signed certificates. For example:
```java
BoxAPIConnection api = new BoxAPIConnection(...);

// to allow self-signed certificates
X509TrustManager trustManager = new X509TrustManager() {
    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
    }

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return new java.security.cert.X509Certificate[]{};
    }
};

// to allow self-signed certificates created for localhost
HostnameVerifier hostnameVerifier = (hostname, session) -> true;

api.configureSslCertificatesValidation(trustManager, hostnameVerifier);
```

If you just need to provide trust manager use `BoxAPIConnection.DEFAULT_HOSTNAME_VERIFIER` as a hostname verifier. 
The same goes for hostname verifier. If you need just to provide it use 
`BoxAPIConnection.DEFAULT_TRUST_MANAGER` as a trust manager.
Example:
```java
BoxAPIConnection api = new BoxAPIConnection(...);
X509TrustManager trustManager = ...
api.configureSslCertificatesValidation(trustManager, BoxAPIConnection.DEFAULT_HOSTNAME_VERIFIER);
```

# Instrumenation of OpenTelemetry

OpenTelemetry is an observability framework and toolkit for creating and managing telemetry data, such as traces,
metrics, and logs. The Box Java SDK can be instrumented with OpenTelemetry to collect telemetry data about the
requests made by the SDK.  

To start, add the [opentelemetry-okhttp-3.0](https://mvnrepository.com/artifact/io.opentelemetry.instrumentation/opentelemetry-okhttp-3.0) dependency to your project.
Next, create a custom class that extends BoxAPIConnection and integrates telemetry in the overridden `createNewCall` method.
Here's an example implementation:
```java
public class BoxAPIConnectionWithTelemetry extends BoxAPIConnection {

    private OkHttpTelemetry telemetry;

    public BoxAPIConnectionWithTelemetry(String accessToken) {
        super(accessToken);
    }

    /**
     * Add required constructors
     */

    public void setTelemetry(OpenTelemetry openTelemetry) {
        this.telemetry = OkHttpTelemetry.builder(openTelemetry).build();
    }

    protected Call createNewCall(OkHttpClient httpClient, Request request) {
        return this.telemetry.newCallFactory(httpClient).newCall(request);
    }
}

```

Please note that you should not modify either `httpClient` or `request` parameters in the createNewCall method.
Altering these parameters can discard the BoxAPIConnection configuration and lead to unexpected behavior.

# OkHttp Default Retry Behavior

When using the Box Java SDK, HTTP requests are made through an underlying OkHttp client. By default, OkHttp enables automatic retries for certain types of connection failures (e.g. network errors) via the `retryOnConnectionFailure(true)` setting. While this can improve reliability for transient issues, this can cause issues during file uploads with `BoxFolder.uploadFile(InputStream, String)`, particularly when the provided `InputStream` is consumed during a failed request and reused in a retry, potentially resulting in a 0-byte file being uploaded.

To leverage the Box SDK’s retry strategy and avoid issues with OkHttp’s default retries, you can disable OkHttp’s automatic retries by customizing the BoxAPIConnection.
To disable OkHttp’s automatic retries, create a custom BoxAPIConnection subclass and override the `modifyHttpClientBuilder` method to set `retryOnConnectionFailure(false)`. This allows the Box SDK to handle retries instead of OkHttp.
Here's an example implementation:
```java
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import okhttp3.OkHttpClient;

public class CustomBoxAPIConnection extends BoxAPIConnection {

    public CustomBoxAPIConnection(BoxConfig boxConfig) {
        super(boxConfig);
    }

    @Override
    protected OkHttpClient.Builder modifyHttpClientBuilder(OkHttpClient.Builder httpClientBuilder) {
        httpClientBuilder.retryOnConnectionFailure(false); // Disable OkHttp retries
        return httpClientBuilder;
    }
}

```
