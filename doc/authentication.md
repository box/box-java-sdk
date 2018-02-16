Authentication
==============

The Box API uses OAuth2 for authentication, which can be difficult to implement.
The SDK makes it easier by providing classes that handle obtaining tokens and
automatically refreshing them.

Ways to Authenticate
--------------------

### Developer Tokens

The fastest way to get started using the API is with developer tokens. A
developer token is simply a short-lived access token that cannot be refreshed
and can only be used with your own account. Therefore, they're only useful for
testing an app and aren't suitable for production. You can obtain a developer
token from your application's [developer
console](https://cloud.app.box.com/developers/services).

The following example creates an API connection with a developer token:

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
```

### Normal Authentication

Using an auth code is the most common way of authenticating with the Box API.
Your application must provide a way for the user to login to Box (usually with a
browser or web view) in order to obtain an auth code.

After a user logs in and grants your application access to their Box account,
they will be redirected to your application's `redirect_uri` which will contain
an auth code. This auth code can then be used along with your client ID and
client secret to establish an API connection.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-AUTH-CODE");
```

### Manual Authentication

In certain advanced scenarios, you may want to obtain an access and refresh
token yourself through manual calls to the API. In this case, you can create an
API connection with the tokens directly.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
```

Auto-Refresh
------------

By default, a `BoxAPIConnection` will automatically refresh the access token if
it has expired. To disable auto-refresh, set the connection's auto-refresh
setting to false with [`setAutoRefresh(false)`][auto-refresh]. Keep in mind that
you will have to manually refresh the access token yourself.

```java
// This connection won't auto-refresh.
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
api.setAutoRefresh(false);

// If the access token expires, you will have to manually refresh it.
api.refresh();
```

[auto-refresh]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setAutoRefresh(boolean)

### App User Authentication

App Users allows your application to provision and control Box accounts that do not have an associated login
and can only be accessed through the Content API by the controlling application. You may authenticate as the service 
account to provision and mange users, or as an individual app user to make calls as that user. See the 
[API documentation](https://github.com/box/box-node-sdk/blob/master/docs/authentication.md#app-user-authentication)
for detailed instruction on how to use app auth. 

Service Account client example:
```java
BoxConfig boxConfig = new BoxConfig("YOUR-CLIENT-ID", "YOUR-CLIENT-SECRET", "ENTERPRISE-ID", 
"JWT-ENCRYPTION-PREFERENCE");

BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);

```

App User client example: 
```java
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection("USER-ID", "CLIENT-ID",
        "CLIENT-SECRET", "ENCRYPTION-PREFERENCE");

BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
```

### Token Exchange

You can exchange a client's access token for one with a lower scope, in order to restrict the permissions for a child 
client or to pass to a less secure location (e.g. a browser-based app). This is useful if you want to use the 
[Box UI Kits](https://developer.box.com/docs/box-ui-elements), since they generally do not need full read/write 
permissions to run. 

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-ACCESS-TOKEN");

String resource = "https://api.box.com/2.0/files/RESOURCE-ID";
List<String> scopes = new ArrayList<String>();
scopes.add("item_preview");
scopes.add("item_content_upload");

ScopedToken token = api.getLowerScopedToken(scopes, resource);
```

The above example will downscope an access token to only allow for previewing an item and uploading an item. 

### Transactional Authentication


Allows applications that do not own content stored in Box(e.g. app-owned content) to be able to use Box as a service
provider rather than a content store. This is currently mostly used for previewing items. For scopes you can choose 
between "item_preview", "item_upload", or "item_delete". See the 
[Getting Started with the New Box View](https://developer.box.com/docs/getting-started-with-new-box-view) for detailed
instruction.


```java
BoxAPIConnection api = BoxTransactionalAPIConnection.getTransactionConnection("YOUR-ACCESS-TOKEN", "item_preview");
```

You can also choose to specify a resource to generate a token for with

```java
BoxAPIConnection api = BoxTransactionalAPIConnection.getTransactionConnection("YOUR-ACCESS-TOKEN", "item_preview", 
"https://api.box.com/2.0/files/YOUR-FILE-ID");
```

