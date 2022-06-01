# Configuration

- [Proxy configuration](#proxy-configuration)
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

# Proxy configuration

To set up proxy
use [BoxApiConnection.setProxy](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxy-java.net.Proxy-)
to set proxy address
and [BoxApiConnection.setProxyUsername](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxyUsername-java.lang.String-) /
[BoxApiConnection.setProxyPassword](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setProxyPassword-java.lang.String-)
to set username and password required by proxy:

```java
BoxAPIConnection api=new BoxAPIConnection("access_token");
Proxy proxy=new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxy_url",8888));
// You can use any subclass of BoxAPIConnection
api.setProxy(proxy);
api.setProxyUsername("proxyUsername");
api.setProxyPassword("proxyPassword");
```

# Configure retries of calls and timeouts

## Maximum retries

To configure how many times API will retry calls
use [BoxApiConnection.setMaxRetryAttempts](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setMaxRetryAttempts-int-):

```java
// You can use any subclass of BoxAPIConnection
api.setMaxRetryAttempts(10);
```

default value for retry attempts is `5`.

## Connection timeout

To set up how log (in miliseconds) API waits to estabilish connection
use [BoxApiConnection.setConnectTimeout](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setConnectTimeout-int-):

```java
// You can use any subclass of BoxAPIConnection
int connectionTimeout = 100; // timeout in miliseconds
api.setConnectTimeout(connectionTimeout);
```

default value is `0` which mean API waits forever to estabilish connection.

## Read timeout

To set up how log (in miliseconds) API waits to read data from connection
use [BoxApiConnection.setReadTimeout](https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setReadTimeout-int-):

```java
// You can use any subclass of BoxAPIConnection
int readTimeout = 100; // timeout in miliseconds
api.setReadTimeout(readTimeout);
```

default value is `0` which mean API waits forever to read data from connection.

## URLs configuration

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
Default value is https://api.box.com/oauth2/token. This metod is deprecated. Use `BoxAPIConnection#setBaseURL(String)` 
instead.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setTokenURL("https://example.com/token");
```

### Revoke URL (deprecated)
The default URL used for invalidating token can be changed by calling `setRevokeURL()` method on `BoxApiConnection`. 
Default value is https://api.box.com/oauth2/revoke. This metod is deprecated. Use `BoxAPIConnection#setBaseURL(String)`
instead.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setRevokeURL("https://example.com/revoke");
```
