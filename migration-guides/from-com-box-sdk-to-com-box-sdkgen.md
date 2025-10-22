# Migration guide: migrate from `com.box.sdk` to `com.box.sdkgen` package

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
  - [Who is this for?](#who-is-this-for)
- [Key differences](#key-differences)
  - [Manager approach](#manager-approach)
  - [Immutable design](#immutable-design)
  - [Consistent method signature](#consistent-method-signature)
- [Authentication](#authentication)
  - [Developer Token](#developer-token)
  - [JWT Auth](#jwt-auth)
    - [Using JWT configuration file](#using-jwt-configuration-file)
    - [Providing JWT configuration manually](#providing-jwt-configuration-manually)
    - [Authenticate user](#authenticate-user)
  - [Client Credentials Grant](#client-credentials-grant)
    - [Obtaining Service Account token](#obtaining-service-account-token)
    - [Obtaining User token](#obtaining-user-token)
  - [Switching between Service Account and User](#switching-between-service-account-and-user)
  - [OAuth 2.0 Auth](#oauth-20-auth)
    - [Get Authorization URL](#get-authorization-url)
    - [Authenticate](#authenticate)
  - [Store token and retrieve token callbacks](#store-token-and-retrieve-token-callbacks)
  - [Downscope token](#downscope-token)
  - [Revoke token](#revoke-token)
- [Configuration](#configuration)
  - [As-User header](#as-user-header)
  - [Custom Base URLs](#custom-base-urls)
- [Convenience methods](#convenience-methods)
  - [Webhook validation](#webhook-validation)
  - [Chunked upload of big files](#chunked-upload-of-big-files)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

Version availability:

- v4: ships only `com.box.sdk` package
- v5: ships both `com.box.sdk` and `com.box.sdkgen` packages (side-by-side)
- v10+: ships only `com.box.sdkgen` module

This document focuses on helping you migrate code from the manually maintained `com.box.sdk` package to the generated `com.box.sdkgen` package.
Many APIs were redesigned for consistency and modern Java patterns, so this guide calls out how to adopt the new shapes safely and incrementally.

Supported migration paths:

- v4 → v5: adopt `com.box.sdkgen` gradually while keeping existing `com.box.sdk` usage
- v4 → v10+: migrate directly to `com.box.sdkgen` package only
- v5 (within the same version): move usage from `com.box.sdk` to `com.box.sdkgen` gradually

For comprehensive API docs with sample code for all methods, see the repository documentation in the root `docs` directory: [`docs/`](../docs/).

We recommend using `com.box.sdkgen` as the preferred SDK going forward. This SDK is automatically generated from the
Box OpenAPI specification, ensuring consistency, reliability, and full API coverage.

Key Benefits:

- Comprehensive Coverage: Supports all Box API endpoints with consistent and predictable method signatures.
- Rapid Feature Availability: Automatically includes new features as soon as they are released in the Box API.
- Explicit Data Models: Includes clear, well-defined models for all API resources to improve readability and maintainability.
- Immutable Design: Built for immutability, making code behavior more predictable and easier to reason about.
- Rich Documentation: Offers detailed usage examples for every API method to help developers get started quickly.

### Who is this for?

- Developers with existing code using `com.box.sdk` who want to start using `com.box.sdkgen` APIs.
- Developers using v5 of Box Java SDK that want to transition usage from `com.box.sdk` to `com.box.sdkgen` within the same app.

## Key differences

### Manager approach

The main difference between the manual `com.box.sdk` package and the nextgen generated one,
is the way how API methods are aggregated into objects.

**Old (`com.box.sdk`)**

Firstly, in the `com.box.sdk` to be able to perform any action on an API object, e.g. `User`, you first had to create its class.
To do it is required to call:

```java
BoxUser user = new BoxUser(api, "12345");
```

to create a class representing an already existing User with id '12345', or create a new one with a call:

```java
BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, "A User");
BoxUser user = new BoxUser(api, createdUserInfo.getID());
```

Then, you could perform any action on created class, which will affect the user, e.g.

```java
BoxUser.Info info = user.new Info();
info.setName(name);
user.updateInfo(info);
```

**New (`com.box.sdkgen`)**

In the `com.box.sdkgen` package, the API methods are grouped into dedicated manager classes, e.g. `User` object
has dedicated `UserManager` class. Each manager class instance is available in `BoxClient` object.
The fields storing references to the managers are named in the plural form of the resource that the
manager handles - `client.users` for `UsersManager`. If you want to perform any operation
connected with a `User` you need to call a respective method of `UserManager`.
For example, to get info about existing user you need to call:

```java
UserFull user = client.users.getUsersById("12345");
```

or to create a new user:

```java
CreateUserRequestBody requestBody =
    new CreateUserRequestBody.Builder("John Doe").build();
UserFull user = client.users.createUser(requestBody);
```

The `UserFull` object returned by both of these methods is a data class - it does not contain any methods to call.
To perform any action on `User` object, you need to still use a `UserManager` method for that.
Usually these methods have a first argument, which accepts id of the object you want to access,
e.g. to update a user name, call method:

```java
UpdateUserByIdRequestBody requestBody =
    new UpdateUserByIdRequestBody.Builder().name("Mary").build();
UserFull updatedUser = client.users.updateUserById(user.getId(), requestBody);
```

### Immutable design

The `com.box.sdkgen` is designed to be mostly immutable. This means that methods,
which used to modify the existing object in `com.box.sdk` now return a new instance of the class with the modified state.
This design pattern is used to avoid side effects and make the code more predictable and easier to reason about.
Methods, which returns a new modified instance of an object, will always have a prefix `with` in their names, e.g.

**New (`com.box.sdkgen`)**

```java
BoxClient client = new BoxClient(auth);
BoxClient asUserClient = client.withAsUserHeader("USER_ID");
```

### Consistent method signature

To facilitate easier work with the new SDK, we have changed the API method signatures to be consistent and unified.

**Old (`com.box.sdk`)**

In the manual version, API methods had numerous parameters, which were not grouped into any objects and were passed as separate arguments, e.g. the method for creating a sign request looked like this:

```java
public static BoxSignRequest.Info createSignRequest(BoxAPIConnection api, List<BoxSignRequestFile> sourceFiles,
                                                        List<BoxSignRequestSigner> signers, String parentFolderId,
                                                        BoxSignRequestCreateParams optionalParams)
```

**New (`com.box.sdkgen`)**

In `com.box.sdkgen`, we have adopted an approach of aggregating parameters into types based on their nature (path, body, query, headers).
This can be seen in the example corresponding to the above:

```java
public SignRequest createSignRequest(
      SignRequestCreateRequest requestBody, CreateSignRequestHeaders headers)
```

According to the convention, if an API endpoint requires a parameter placed in the URL path,
it will appear at the beginning of our method, such as `fileId` in this case.
When a request allows for a body, as in `POST` or `PUT`, the method includes a parameter named `requestBody`.
Following that, the method signature may include the `queryParams` parameter, followed by `headers`.
The types of parameters `requestBody`, `queryParams`, and `headers` are specific to each endpoint and, in their definition,
encompass all fields allowed by the API.
It's worth noting here that when all fields of a particular type are optional, the entire parameter becomes optional as well.
This allows us to pass only the parameters we actually want to provide when calling a given method,
without the risk of not providing a sufficient number of parameters.

## Authentication

The `com.box.sdkgen` package offers the same authentication methods as the `com.box.sdk` one.
Let's see the differences of their usage:

### Developer Token

**Old (`com.box.sdk`)**

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
```

The `com.box.sdkgen` package provides a convenient `BoxDeveloperTokenAuth`, which allows authenticating
using developer token without necessity to provide a Client ID and Client Secret

**New (`com.box.sdkgen`)**

```java
BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("YOUR-DEVELOPER-TOKEN");
BoxClient client = new BoxClient(auth);
```

### JWT Auth

#### Using JWT configuration file

**Old (`com.box.sdk`)**

The static method, which reads the JWT configuration file has been changed:

```java
Reader reader = new FileReader("src/example/config/config.json");
BoxConfig boxConfig = BoxConfig.readFrom(reader);
IAccessTokenCache tokenCache = new InMemoryLRUAccessTokenCache(100);
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, tokenCache);
```

**New (`com.box.sdkgen`)**

```java
TokenStorage tokenStorage = new InMemoryTokenStorage(); // or any other implementation of TokenStorage
JWTConfig config = JWTConfig.fromConfigFile("src/example/config/config.json", tokenStorage);
BoxJWTAuth auth = new BoxJWTAuth(config);
BoxClient client = new BoxClient(auth);
```

#### Providing JWT configuration manually

Some params in `JWTConfig` constructor have slightly different names than one in old `JWTAuth` class.

**Old (`com.box.sdk`)**

```java
JWTEncryptionPreferences jwtPreferences = new JWTEncryptionPreferences();
jwtPreferences.setPublicKeyID("PUBLIC-KEY-ID");
jwtPreferences.setPrivateKeyPassword("PRIVATE-KEY-PASSWORD");
jwtPreferences.setPrivateKey("PRIVATE-KEY");
jwtPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection
    .getUserConnection("USER-ID", "CLIENT-ID","CLIENT-SECRET", jwtPreferences, accessTokenCache);

BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
```

**New (`com.box.sdkgen`)**

```java
TokenStorage tokenStorage = new InMemoryTokenStorage();
JWTConfig config = new JWTConfig.Builder("YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET", "JWT_KEY_ID", "PRIVATE_KEY", "PRIVATE_KEY_PASSWORD")
    .enterpriseId("123456")
    .tokenStorage(tokenStorage)
    .build();
BoxJWTAuth auth = new BoxJWTAuth(config);
BoxClient client = new BoxClient(auth);
```

#### Authenticate user

In `com.box.sdk` package method for user authentication was named `BoxDeveloperEditionAPIConnection getUserConnection(String userId, BoxConfig boxConfig, IAccessTokenCache accessTokenCache)`
and was accepting user id. The method was returning a new instance of `BoxDeveloperEditionAPIConnection` class, which was exchanging the existing token with the one with the user access.

**Old (`com.box.sdk`)**

```java
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getUserConnection("USER_ID", boxConfig, tokenCache);
```

**New (`com.box.sdkgen`)**

In `com.box.sdkgen`, to authenticate as user you need to call
`public BoxJWTAuth withUserSubject(String userId, TokenStorage tokenStorage)` method with id of the user
to authenticate. The method returns a new instance of `BoxJWTAuth` class, which will perform authentication call
in scope of the user on the first API call. The `tokenStorage` parameter is optional and allows to provide a custom
token storage for the new instance of `BoxJWTAuth` class. The new auth instance can be used to create a new user client
instance.

```java
BoxJWTAuth userAuth = auth.withUserSubject("USER_ID");
BoxClient userClient = new BoxClient(userAuth);
```

### Client Credentials Grant

#### Obtaining Service Account token

To authenticate as enterprise, the only difference between `com.box.sdk` and `com.box.sdkgen` packages is using the `CCGConfig` as a middle step.

**Old (`com.box.sdk`)**

```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.applicationServiceAccountConnection(
    "client_id",
    "client_secret",
    "enterprise_id"
);
```

**New (`com.box.sdkgen`)**

```java
CCGConfig config = new CCGConfig.Builder("YOUR_CLIENT", "YOUR_CLIENT_SECRET")
    .enterpriseId("ENTERPRISE_ID")
    .build();
BoxCCGAuth auth = new BoxCCGAuth(config);
BoxClient client = new BoxClient(auth);
```

#### Obtaining User token

To authenticate as user, the only difference between the versions is using the `CCGConfig` as a middle step.

**Old (`com.box.sdk`)**

```java
BoxCCGAPIConnection api = BoxCCGAPIConnection.userConnection(
    "client_id",
    "client_secret",
    "user_id"
);
```

**New (`com.box.sdkgen`)**

```java
CCGConfig config = new CCGConfig.Builder("YOUR_CLIENT", "YOUR_CLIENT_SECRET")
    .userId("USER_ID")
    .build();
BoxCCGAuth auth = new BoxCCGAuth(config);
BoxClient client = new BoxClient(auth);
```

### Switching between Service Account and User

In `com.box.sdk` package, if you want to switch between the Service Account and User, you need to create another API connection.

In `com.box.sdkgen`, to keep the immutability design, the methods switching authenticated subject were replaced with methods
returning a new instance of `BoxCCGAuth` class. The new instance will fetch a new token on the next API call.
The new auth instance can be used to create a new client instance. You can also specify `tokenStorage` parameter
to provide a custom token storage for the new instance.
The old instance of `BoxCCGAuth` class will remain unchanged and will still use the old token.

**New (`com.box.sdkgen`)**

```java
BoxCCGAuth userAuth = auth.withUserSubject("USER_ID");
BoxClient userClient = new BoxClient(userAuth);
```

```java
BoxCCGAuth entAuth = auth.withEnterpriseSubject("ENTERPRISE_ID");
BoxClient entClient = new BoxClient(entAuth);
```

### OAuth 2.0 Auth

#### Get Authorization URL

To get authorization url in the `com.box.sdkgen` package, you need to first create the `BoxOAuth` class using
`OAuthConfig` class. Then to get authorization url, call `public String getAuthorizeUrl(GetAuthorizeUrlOptions options)`.
Note that this method accepts the instance of `GetAuthorizeUrlOptions` class, which allows specifying extra options to API call.

**Old (`com.box.sdk`)**

In `com.box.sdk`, we did not have any method to get the authorization URL. Instead, we had to manually create the URL.

```java
String authorizationUrl = "https://account.box.com/api/oauth2/authorize?client_id=[CLIENT_ID]&response_type=code";
```

**New (`com.box.sdkgen`)**

```java
BoxOAuth auth = new BoxOAuth(new OAuthConfig.Builder("CLIENT_ID", "CLIENT_SECRET").build());
String authorizationUrl = auth.getAuthorizeUrl();
```

#### Authenticate

The method for authenticating using the authorization code has been changed. With the `com.box.sdk` package of the SDK,
you had to provide the authorization code to the `BoxAPIConnection` class constructor. In the `com.box.sdkgen` package,
you need to call the `public AccessToken getTokensAuthorizationCodeGrant(String authorizationCode)` method of the `BoxOAuth` class.
The method now returns an AccessToken object with `accessToken` and `refreshToken` fields,
while the old one was creating a new instance of `BoxAPIConnection` class.

**Old (`com.box.sdk`)**

```java
BoxAPIConnection client = new BoxAPIConnection(
    "[CLIENT_ID]",
    "[CLIENT_SECRET]",
    "[CODE]"
);
```

**New (`com.box.sdkgen`)**

```java
auth.getTokensAuthorizationCodeGrant("AUTHORIZATION_CODE");
BoxClient client = new BoxClient(auth);
```

### Store token and retrieve token callbacks

In the `com.box.sdkgen` package you can define your own class delegated for storing and retrieving a token. It has to inherit from
`TokenStorage` and implement all of its abstract methods. Next step would be to pass an instance of this class to the
AuthConfig constructor.

**New (`com.box.sdkgen`)**

```java
TokenStorage customTokenStorage = new TokenStorage() {
    @Override
    public void store(AccessToken accessToken) {
        // Store the access token
    }

    @Override
    public AccessToken get() {
        // Retrieve the access token
        return null;
    }

    @Override
    public void clear() {
        // Clear the access token
    }
};

OAuthConfig config = new OAuthConfig.Builder("CLIENT_ID", "CLIENT_SECRET")
    .tokenStorage(customTokenStorage)
    .build();
BoxOAuth auth = new BoxOAuth(config);
```

or reuse one of the provided implementations: `InMemoryTokenStorage`:

```java
TokenStorage tokenStorage = new InMemoryTokenStorage();
OAuthConfig config = new OAuthConfig.Builder("CLIENT_ID", "CLIENT_SECRET")
    .tokenStorage(tokenStorage)
    .build();
BoxOAuth auth = new BoxOAuth(config);
```

### Downscope token

The process of downscoping token in the new version is similar to the old one. The main difference is that in the new version
you need to call `downscopeToken` method of the `BoxOAuth` class instead of `getLowerScopedToken` method of the `BoxAPIConnection` class.

**Old (`com.box.sdk`)**

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");

String resource = "https://api.box.com/2.0/files/RESOURCE-ID";
List<String> scopes = new ArrayList<String>();
scopes.add("item_preview");
scopes.add("item_content_upload");

ScopedToken token = api.getLowerScopedToken(scopes, resource);
```

**New (`com.box.sdkgen`)**

```java
String resource = "https://api.box.com/2.0/files/123456789";
List<String> scopes = List.of("item_preview");
AccessToken downscopedToken = auth.downscopeToken(scopes, resource, null, null);
BoxDeveloperTokenAuth downscopedAuth = new BoxDeveloperTokenAuth(downscopedToken.getAccessToken());
BoxClient downscopedClient = new BoxClient(downscopedAuth);
```

### Revoke token

To revoke current client's tokens in the `com.box.sdkgen` package, you need to call `revokeToken` method of the auth class instead of
`revoke` method.

**Old (`com.box.sdk`)**

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.revokeToken();
```

**New (`com.box.sdkgen`)**

```java
client.auth.revokeToken()
```

## Configuration

### As-User header

The As-User header is used by enterprise admins to make API calls on behalf of their enterprise's users.
This requires the API request to pass an `As-User: USER-ID` header. The following examples assume that the client has
been instantiated with an access token with appropriate privileges to make As-User calls.

In `com.box.sdk` package you could call client `asUser(String userID)` method to create a new client to impersonate the provided user.

**Old (`com.box.sdk`)**

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");
api.asUser("USER-ID");
```

**New (`com.box.sdkgen`)**

In the new version, the method was renamed to `withAsUserHeader` in the `BoxClient` class,
and returns a new instance of `BoxClient` class with the As-User header appended to all API calls made by the client.
The method accepts only user id as a parameter.

```java
BoxClient userClient = client.withAsUserHeader("USER-ID");
```

Additionally `BoxClient` offers a `withExtraHeaders(Map<String, String> extraHeaders)`
method, which allows you to specify the custom set of headers, which will be included in every API call made by client.
Calling the `client.withExtraHeaders()` method creates a new client, leaving the original client unmodified.

```java
BoxClient clientWithExtraHeaders = client.withExtraHeaders(new HashMap<>() {{
    put("X-My-Header", "124");
}});

```

### Custom Base URLs

**Old (`com.box.sdk`)**

In `com.box.sdk` you could specify the custom base URLs, which will be used for API calls made by setting
the new values of static variables of the `API` class.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
api.setBaseAppUrl("https://example.app.com");
api.setBaseURL("https://example.com");
api.setUploadURL("https://upload.example.com");
api.setTokenURL("https://example.com/token");
```

**New (`com.box.sdkgen`)**

In `com.box.sdkgen` this functionality has been implemented as part of the `BoxClient` class.
By calling the `client.withCustomBaseUrls()` method, you can specify the custom base URLs that will be used for API
calls made by client. Following the immutability pattern, this call creates a new client, leaving the original client unmodified.

```java
BaseUrls baseUrls = new BaseUrls.Builder()
    .baseUrl("https://new-base-url.com")
    .uploadUrl("https://my-company-upload-url.com")
    .oauth2Url("https://my-company.com/oauth2")
    .build();
BoxClient clientWithCustomBaseUrl = client.withCustomBaseUrls(baseUrls);
```

## Convenience methods

### Webhook validation

Webhook validation is used to validate a webhook message by verifying the signature and the delivery timestamp.

**Old (`com.box.sdk`)**

In `com.box.sdk`, when you receive a webhook message from Box, to validate that it actually came from Box
you need to call `BoxWebHookSignatureVerifier#verify(String sigVersion, String sigAlgorithm, String primarySignature, String secondarySignature, String payload, String deliveryTimestamp)` method.
It would return a `boolean` value indicating whether the message was valid.

```java
// Webhook message contents are shown for demonstration purposes
// Normally these would come from your HTTP handler

// Webhook message HTTP body
String messagePayload = "{"
+ "\"type\":\"webhook_event","
+ "\"webhook\":{"
+   "\"id\":\"1234567890\""
+ "},"
+ "\"trigger\":\"FILE.UPLOADED\","
+ "\"source\":{"
+   "\"id\":\"1234567890\","
+   "\"type\":\"file\","
+   "\"name\":\"Test.txt\""
+ "}}";

// Webhook message HTTP headers
Map<String, String> messageHeaders = new HashMap<String, String>();
headers.put("BOX-DELIVERY-ID", "f96bb54b-ee16-4fc5-aa65-8c2d9e5b546f");
headers.put("BOX-DELIVERY-TIMESTAMP", "2020-01-01T00:00:00-07:00");
headers.put("BOX-SIGNATURE-ALGORITHM", "HmacSHA256");
headers.put("BOX-SIGNATURE-PRIMARY", "6TfeAW3A1PASkgboxxA5yqHNKOwFyMWuEXny/FPD5hI=");
headers.put("BOX-SIGNATURE-SECONDARY", "v+1CD1Jdo3muIcbpv5lxxgPglOqMfsNHPV899xWYydo=");
headers.put("BOX-SIGNATURE-VERSION", "1");

// Your application's webhook keys, obtained from the Box Developer Console
String primaryKey = "4py2I9eSFb0ezXH5iPeQRcFK1LRLCdip";
String secondaryKey = "Aq5EEEjAu4ssbz8n9UMu7EerI0LKj2TL";

BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(primaryKey, secondaryKey);
boolean isValidMessage = verifier.verify(
  headers.get("BOX-SIGNATURE-VERSION"),
  headers.get("BOX-SIGNATURE-ALGORITHM"),
  headers.get("BOX-SIGNATURE-PRIMARY"),
  headers.get("BOX-SIGNATURE-SECONDARY"),
  messagePayload,
  headers.get("BOX-DELIVERY-TIMESTAMP")
);
```

**New (`com.box.sdkgen`)**

In the `com.box.sdkgen` package of ths SDK, the `WebhooksManager.validateMessage()` method requires the `messagePayload` to be of type `string`,
map of headers to be of type `Map<String, String>`, and the primary and secondary keys to be of type `String`.

```java
// Webhook message HTTP body
String messagePayload = "{"
        + "\"type\":\"webhook_event","
        + "\"webhook\":{"
        +   "\"id\":\"1234567890\""
        + "},"
        + "\"trigger\":\"FILE.UPLOADED\","
        + "\"source\":{"
        +   "\"id\":\"1234567890\","
        +   "\"type\":\"file\","
        +   "\"name\":\"Test.txt\""
        + "}}";

// Webhook message HTTP headers
Map<String, String> messageHeaders = new HashMap<String, String>();
headers.put("BOX-DELIVERY-ID", "f96bb54b-ee16-4fc5-aa65-8c2d9e5b546f");
headers.put("BOX-DELIVERY-TIMESTAMP", "2020-01-01T00:00:00-07:00");
headers.put("BOX-SIGNATURE-ALGORITHM", "HmacSHA256");
headers.put("BOX-SIGNATURE-PRIMARY", "6TfeAW3A1PASkgboxxA5yqHNKOwFyMWuEXny/FPD5hI=");
headers.put("BOX-SIGNATURE-SECONDARY", "v+1CD1Jdo3muIcbpv5lxxgPglOqMfsNHPV899xWYydo=");
headers.put("BOX-SIGNATURE-VERSION", "1");

// Your application's webhook keys, obtained from the Box Developer Console
String primaryKey = "4py2I9eSFb0ezXH5iPeQRcFK1LRLCdip";
String secondaryKey = "Aq5EEEjAu4ssbz8n9UMu7EerI0LKj2TL";

boolean isValidMessage = WebhooksManager.validateMessage(
        messagePayload, messageHeaders, primaryKey, secondaryKey);
)
```

### Chunked upload of big files

For large files or in cases where the network connection is less reliable, you may want to upload the file in parts.
This allows a single part to fail without aborting the entire upload, and failed parts are being retried automatically.

**Old (`com.box.sdk`)**

In `com.box.sdk`, you could use the `uploadLargeFile` method of the `BoxFolder` class to upload a large file.
This method accepted a `FileInputStream` as the input stream and the file size as a parameter. The method also required

```java
File myFile = new File("My Large_File.txt");
FileInputStream stream = new FileInputStream(myFile);

BoxFolder rootFolder = BoxFolder.getRootFolder(api);
BoxFile.Info fileInfo = rootFolder.uploadLargeFile(inputStream, "My_Large_File.txt", myFile.length());
```

**New (`com.box.sdkgen`)**

In `com.box.sdkgen`, the equivalent method is `chunked_uploads.uploadBigFile()`. It accepts a file-like object
as the `file` parameter, and the `fileName` and `fileSize` parameters are now passed as arguments.
The `parentFolderId` parameter is also required to specify the folder where the file will be uploaded.

```java
InputStream file = new FileInputStream(myFile);
String fileName = "My_Large_File.txt";
long fileSize = 1234556L;
String parentFolderId = "123456789";

File uploadedFile = client.getChunkedUploads().uploadBigFile(file, fileName, fileSize, parentFolderId);
```
