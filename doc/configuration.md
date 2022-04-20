Configuration
=============

- [URLs configuration](#urls-configuration)
    - [Base URL](#base-url)
    - [Base Upload URL](#base-upload-url)
    - [Base App URL](#base-app-url)
    - [Token URL](#token-url-deprecated)
    - [Revoke URL](#revoke-url-deprecated)

URLs configuration
------------------

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
