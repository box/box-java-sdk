Configuration
=============

- [URLs configuration](#urls-configuration)
    - [Base URL](#base-url)
    - [Base App URL](#base-app-url)
    - [Token URL](#token-url)
    - [Revoke URL](#revoke-url)
    - [Upload URL](#upload-url)

URLs configuration
------------------

### Base URL
The default base URL used for making API calls to Box can be changed by calling `setBaseURL()` 
method on `BoxApiConnection`. Default value is https://api.box.com/2.0/.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseURL("https://example.com");
```

### Base App URL
The default base app URL can be changed by calling `setBaseAppUrl()` method on `BoxApiConnection`. 
Default value is https://app.box.com.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseAppUrl("https://example.app.com");
```

### Token URL
The default URL used for getting token can be changed by calling `setTokenURL()` method on `BoxApiConnection`. 
Default value is https://api.box.com/oauth2/token.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setTokenURL("https://example.com/token");
```

### Revoke URL
The default URL used for invalidating token can be changed by calling `setRevokeURL()` method on `BoxApiConnection`. 
Default value is https://api.box.com/oauth2/revoke.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setRevokeURL("https://example.com/revoke");
```

### Upload URL
The default URL used for uploads can be changed by calling `setBaseUploadURL()` method on `BoxApiConnection`. 
Default value is https://upload.box.com/api/2.0/.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");

api.setBaseUploadURL("https://example.upload.com");
```
